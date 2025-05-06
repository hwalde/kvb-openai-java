package de.entwickler.training.exercise44;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.AssistantMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.SystemMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Übung 44: Agent Abschluss (Qwen)
 *
 * Ziel: Eine Logik implementieren, damit der Agent erkennt, wann die Aufgabe abgeschlossen ist
 * und keine weiteren Tool-Aufrufe nötig sind, um dann eine finale Zusammenfassung oder Antwort zu geben.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Kriterien zur Erkennung des Abschlusses
 * - Implementierung der isTaskComplete-Logik
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erweitere den Agenten aus Übung 43 um eine verbesserte Abschlusslogik
 * 3. Implementiere die isTaskComplete-Methode, die erkennt, wann die Aufgabe abgeschlossen ist
 * 4. Optional: Implementiere eine explizite Abschlussnachricht (ähnlich attempt_completion in RooCode)
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class AgentCompletion {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        
        // Erstelle eine Instanz des Agenten
        Agent agent = new Agent();
        
        // Führe eine Aufgabe aus, die mehrere Schritte erfordert
        String initialPrompt = "Finde alle Java-Dateien im Projekt und lies dann den Inhalt der ersten gefundenen Datei.";
        String finalResponse = agent.runTask(initialPrompt);
        
        System.out.println("Anfrage an den Agenten:");
        System.out.println(initialPrompt);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Finale Antwort des Agenten:");
        System.out.println(finalResponse);
        
        // Optional: Teste den Agenten mit einer einfacheren Aufgabe, die keinen Tool-Aufruf erfordert
        System.out.println("\n----------------------------------------\n");
        
        String simplePrompt = "Was ist Java?";
        String simpleResponse = agent.runTask(simplePrompt);
        
        System.out.println("Einfache Anfrage an den Agenten:");
        System.out.println(simplePrompt);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Antwort auf die einfache Anfrage:");
        System.out.println(simpleResponse);
    }
    
    /**
     * Agent-Klasse, die mit dem LLM interagiert und mehrere Tools verwendet, um eine komplexe Aufgabe zu lösen
     */
    static class Agent {
        // System-Prompt im RooCode-Stil mit zwei Tools und Anweisungen zum Abschluss
        private static final String SYSTEM_PROMPT = """
                # Tools
                
                ## search_files
                Description: Sucht nach Dateien mit einem bestimmten Muster.
                Parameters:
                - pattern: (required) Das Suchmuster (z.B. Dateiendung wie ".java").
                
                Usage:
                <search_files>
                <pattern>Suchmuster</pattern>
                </search_files>
                
                Example:
                <search_files>
                <pattern>.java</pattern>
                </search_files>
                
                ## read_file
                Description: Liest den Inhalt einer Datei.
                Parameters:
                - path: (required) Der Pfad zur Datei, die gelesen werden soll.
                
                Usage:
                <read_file>
                <path>Pfad/zur/datei.txt</path>
                </read_file>
                
                Example:
                <read_file>
                <path>src/main/java/Main.java</path>
                </read_file>
                
                ## attempt_completion
                Description: Verwende dieses Tool, wenn du glaubst, dass die Aufgabe abgeschlossen ist.
                Parameters:
                - summary: (required) Eine Zusammenfassung der erledigten Aufgabe.
                
                Usage:
                <attempt_completion>
                <summary>Zusammenfassung der erledigten Aufgabe</summary>
                </attempt_completion>
                
                Example:
                <attempt_completion>
                <summary>Ich habe alle Java-Dateien gefunden und den Inhalt der ersten Datei gelesen.</summary>
                </attempt_completion>
                
                # Anweisungen
                
                Du bist ein hilfreicher Assistent, der Dateien suchen und lesen kann.
                - Wenn der Benutzer nach Dateien suchen möchte, verwende das search_files Tool.
                - Wenn der Benutzer den Inhalt einer Datei lesen möchte, verwende das read_file Tool.
                - Wenn du glaubst, dass die Aufgabe abgeschlossen ist, verwende das attempt_completion Tool.
                Du kannst mehrere Tools nacheinander verwenden, um komplexe Aufgaben zu lösen.
                Antworte in deutscher Sprache.
                Wenn du eine Frage beantworten kannst, ohne ein Tool zu verwenden, antworte direkt.
                """;
        
        // Chat-Verlauf
        private List<io.github.sashirestela.openai.domain.chat.ChatMessage> messages;
        
        // OpenAI-Client
        private final io.github.sashirestela.openai.SimpleOpenAI openAIClient;
        
        // Modell-ID
        private final String modelId = "/models/qwen3-32b-fp8";
        
        // Debug-Modus (für detaillierte Ausgaben)
        private final boolean debug = true;
        
        /**
         * Konstruktor
         */
        public Agent() {
            this.openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
            resetConversation();
        }
        
        /**
         * Setzt die Konversation zurück
         */
        public void resetConversation() {
            this.messages = new ArrayList<>();
            this.messages.add(SystemMessage.of(SYSTEM_PROMPT));
        }
        
        /**
         * Führt eine Aufgabe aus, indem mit dem LLM interagiert wird
         * 
         * @param initialPrompt Der initiale Prompt des Benutzers
         * @return Die finale Antwort des LLM
         */
        public String runTask(String initialPrompt) {
            // TODO: Implementiere die Agenten-Schleife mit verbesserter Abschlusslogik
            // Hier ist ein Beispiel für die Implementierung:
            
            // Konversation zurücksetzen für jede neue Aufgabe
            resetConversation();
            
            // Initialen Prompt hinzufügen
            messages.add(UserMessage.of(initialPrompt));
            
            if (debug) System.out.println("[DEBUG] Initialer Prompt: " + initialPrompt);
            
            // Antwort des LLM
            String llmResponse = "";
            
            // Maximale Anzahl von Iterationen (um Endlosschleifen zu vermeiden)
            int maxIterations = 10;
            int iterations = 0;
            
            // Agenten-Schleife
            while (iterations < maxIterations) {
                iterations++;
                
                if (debug) System.out.println("[DEBUG] Iteration " + iterations);
                
                // Anfrage an das LLM senden
                var chatRequest = ChatRequest.builder()
                        .model(modelId)
                        .messages(messages)
                        .build();
                
                var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
                llmResponse = chatResponse.firstContent();
                
                if (debug) System.out.println("[DEBUG] LLM-Antwort: " + llmResponse);
                
                // Antwort des LLM zum Chat-Verlauf hinzufügen
                messages.add(AssistantMessage.of(llmResponse));
                
                // Prüfen, ob die Aufgabe abgeschlossen ist
                if (isTaskComplete(llmResponse)) {
                    if (debug) System.out.println("[DEBUG] Aufgabe abgeschlossen");
                    
                    // Wenn es ein attempt_completion Tool-Aufruf ist, extrahiere die Zusammenfassung
                    ToolCall completionToolCall = parseToolCall(llmResponse);
                    if (completionToolCall != null && "attempt_completion".equals(completionToolCall.toolName)) {
                        String summary = completionToolCall.parameters.get("summary");
                        
                        // Füge eine abschließende Nachricht hinzu
                        messages.add(UserMessage.of("TASK_COMPLETED: " + summary));
                        
                        // Sende eine letzte Anfrage, um eine schön formatierte Antwort zu erhalten
                        var finalRequest = ChatRequest.builder()
                                .model(modelId)
                                .messages(messages)
                                .build();
                        
                        var finalResponse = openAIClient.chatCompletions().create(finalRequest).join();
                        return finalResponse.firstContent();
                    }
                    
                    // Wenn es keine attempt_completion ist, aber die Aufgabe trotzdem abgeschlossen ist
                    return llmResponse;
                }
                
                // Antwort verarbeiten
                String toolResult = processLlmResponse(llmResponse);
                
                if (debug) System.out.println("[DEBUG] Tool-Ergebnis: " + toolResult);
                
                // Tool-Ergebnis als neue User-Nachricht hinzufügen
                messages.add(UserMessage.of("TOOL_RESULT: " + toolResult));
            }
            
            // Wenn die maximale Anzahl von Iterationen erreicht wurde
            return "Die maximale Anzahl von Iterationen wurde erreicht. Letzte Antwort des LLM: " + llmResponse;
        }
        
        /**
         * Verarbeitet die Antwort des LLM
         * 
         * @param response Die Antwort des LLM
         * @return Das Ergebnis der Tool-Ausführung oder null, wenn kein Tool aufgerufen wurde
         */
        private String processLlmResponse(String response) {
            // TODO: Implementiere die Verarbeitung der LLM-Antwort
            // Hier ist ein Beispiel für die Implementierung:
            
            // Tool-Aufruf parsen
            ToolCall toolCall = parseToolCall(response);
            
            // Wenn ein Tool-Aufruf erkannt wurde, Tool ausführen
            if (toolCall != null) {
                // Wenn es ein attempt_completion Tool-Aufruf ist, gib eine spezielle Antwort zurück
                if ("attempt_completion".equals(toolCall.toolName)) {
                    return "COMPLETION_ACKNOWLEDGED";
                }
                
                return executeTool(toolCall);
            }
            
            // Wenn kein Tool-Aufruf erkannt wurde
            return "Kein Tool-Aufruf erkannt. Die Antwort wird als direkte Antwort auf die Anfrage betrachtet.";
        }
        
        /**
         * Führt ein Tool aus
         * 
         * @param toolCall Der Tool-Aufruf
         * @return Das Ergebnis der Tool-Ausführung
         */
        private String executeTool(ToolCall toolCall) {
            // TODO: Implementiere die Tool-Ausführung für beide Tools
            // Hier ist ein Beispiel für die Implementierung:
            
            if ("search_files".equals(toolCall.toolName)) {
                String pattern = toolCall.parameters.get("pattern");
                return simulateSearchFiles(pattern);
            } else if ("read_file".equals(toolCall.toolName)) {
                String path = toolCall.parameters.get("path");
                return simulateReadFile(path);
            }
            
            return "Unbekanntes Tool: " + toolCall.toolName;
        }
        
        /**
         * Prüft, ob die Aufgabe abgeschlossen ist
         * 
         * @param response Die Antwort des LLM
         * @return true, wenn die Aufgabe abgeschlossen ist, sonst false
         */
        private boolean isTaskComplete(String response) {
            // TODO: Implementiere die verbesserte Prüfung, ob die Aufgabe abgeschlossen ist
            // Hier ist ein Beispiel für die Implementierung:
            
            // 1. Prüfen, ob ein attempt_completion Tool-Aufruf vorhanden ist
            ToolCall toolCall = parseToolCall(response);
            if (toolCall != null && "attempt_completion".equals(toolCall.toolName)) {
                return true;
            }
            
            // 2. Prüfen, ob die Antwort keinen Tool-Aufruf enthält (direkte Antwort)
            if (toolCall == null) {
                // Wenn die Antwort keinen Tool-Aufruf enthält, ist sie wahrscheinlich eine direkte Antwort
                // auf eine Frage, die kein Tool erfordert
                return true;
            }
            
            // 3. Wenn keine der obigen Bedingungen zutrifft, ist die Aufgabe noch nicht abgeschlossen
            return false;
        }
        
        /**
         * Simuliert die Suche nach Dateien mit einem bestimmten Muster
         * 
         * @param pattern Das Suchmuster
         * @return Eine Liste von gefundenen Dateien
         */
        private String simulateSearchFiles(String pattern) {
            // In einer echten Anwendung würde hier eine tatsächliche Dateisuche durchgeführt werden
            if (".java".equals(pattern) || "*.java".equals(pattern) || pattern.contains("java")) {
                return "src/main/java/Main.java\nsrc/main/java/de/entwickler/training/util/OpenAIClientService.java\nsrc/main/java/de/entwickler/training/exercise01/FirstApiRequest.java";
            } else if (".txt".equals(pattern) || "*.txt".equals(pattern) || pattern.contains("txt")) {
                return "README.txt\ndocs/installation.txt";
            } else if (".md".equals(pattern) || "*.md".equals(pattern) || pattern.contains("md")) {
                return "README.md\ndocs/api.md";
            } else {
                return "Keine Dateien gefunden, die dem Muster '" + pattern + "' entsprechen.";
            }
        }
        
        /**
         * Simuliert das Lesen einer Datei
         * 
         * @param path Der Pfad zur Datei
         * @return Der Inhalt der Datei
         */
        private String simulateReadFile(String path) {
            // In einer echten Anwendung würde hier der tatsächliche Dateiinhalt gelesen werden
            if (path.contains("Main.java")) {
                return """
                        package de.entwickler.training;
                        
                        public class Main {
                            public static void main(String[] args) {
                                System.out.println("Hello, World!");
                            }
                        }
                        """;
            } else if (path.contains("OpenAIClientService.java")) {
                return """
                        package de.entwickler.training.util;
                        
                        import io.github.sashirestela.openai.SimpleOpenAI;
                        
                        public class OpenAIClientService {
                            private static OpenAIClientService instance;
                            private SimpleOpenAI simpleOpenAI;
                            
                            private OpenAIClientService() {
                                // Initialisierung
                            }
                            
                            public static synchronized OpenAIClientService getInstance() {
                                if (instance == null) {
                                    instance = new OpenAIClientService();
                                }
                                return instance;
                            }
                            
                            public SimpleOpenAI getOpenAIClient() {
                                // Client zurückgeben
                                return simpleOpenAI;
                            }
                        }
                        """;
            } else if (path.contains("FirstApiRequest.java")) {
                return """
                        package de.entwickler.training.exercise01;
                        
                        import de.entwickler.training.util.OpenAIClientService;
                        
                        public class FirstApiRequest {
                            public static void main(String[] args) {
                                var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
                                // Erste API-Anfrage
                            }
                        }
                        """;
            } else {
                return "Datei nicht gefunden: " + path;
            }
        }
        
        /**
         * Parst einen Tool-Aufruf aus der Antwort des Modells
         * 
         * @param response Die Antwort des Modells
         * @return Ein ToolCall-Objekt oder null, wenn kein Tool-Aufruf gefunden wurde
         */
        private ToolCall parseToolCall(String response) {
            // Regex-Pattern für den Tool-Aufruf
            Pattern toolPattern = Pattern.compile("<([a-zA-Z_]+)>(.*?)</\\1>", Pattern.DOTALL);
            Matcher toolMatcher = toolPattern.matcher(response);
            
            if (toolMatcher.find()) {
                ToolCall toolCall = new ToolCall();
                toolCall.toolName = toolMatcher.group(1);
                String toolContent = toolMatcher.group(2);
                
                // Regex-Pattern für die Parameter
                Pattern paramPattern = Pattern.compile("<([a-zA-Z_]+)>(.*?)</\\1>", Pattern.DOTALL);
                Matcher paramMatcher = paramPattern.matcher(toolContent);
                
                while (paramMatcher.find()) {
                    String paramName = paramMatcher.group(1);
                    String paramValue = paramMatcher.group(2).trim();
                    toolCall.parameters.put(paramName, paramValue);
                }
                
                return toolCall;
            }
            
            return null;
        }
    }
    
    /**
     * Helfer-Klasse zur Repräsentation eines Tool-Aufrufs
     */
    static class ToolCall {
        String toolName;
        Map<String, String> parameters = new HashMap<>();
    }
}
