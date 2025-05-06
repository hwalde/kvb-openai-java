package de.entwickler.training.exercise43;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.AssistantMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.SystemMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Übung 43: Agent mit mehreren Schritten (Qwen)
 *
 * Ziel: Einen Agenten implementieren, der eine Aufgabe löst, die mehrere Interaktionen
 * (Tool-Aufrufe) mit dem LLM erfordert.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Persistenz des Chat-Verlaufs über mehrere Schleifendurchläufe
 * - Verarbeitung aufeinanderfolgender Tool-Aufrufe
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Implementiere einen Agenten mit zwei Tools: search_files und read_file
 * 3. Gib dem Agenten eine Aufgabe, die beide Tools nacheinander erfordert:
 *    "Finde alle Java-Dateien im Projekt und lies dann den Inhalt der ersten gefundenen Datei."
 * 4. Beobachte, wie der Agent die Aufgabe in mehreren Schritten löst
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class AgentWithMultipleSteps {

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
    }
    
    /**
     * Agent-Klasse, die mit dem LLM interagiert und mehrere Tools verwendet, um eine komplexe Aufgabe zu lösen
     */
    static class Agent {
        // System-Prompt im RooCode-Stil mit zwei Tools
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
                
                # Anweisungen
                
                Du bist ein hilfreicher Assistent, der Dateien suchen und lesen kann.
                - Wenn der Benutzer nach Dateien suchen möchte, verwende das search_files Tool.
                - Wenn der Benutzer den Inhalt einer Datei lesen möchte, verwende das read_file Tool.
                Du kannst mehrere Tools nacheinander verwenden, um komplexe Aufgaben zu lösen.
                Antworte in deutscher Sprache.
                Wenn du eine Aufgabe abgeschlossen hast, beginne deine Antwort mit "AUFGABE ABGESCHLOSSEN:".
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
            // TODO: Implementiere die Agenten-Schleife für mehrere Schritte
            // Hier ist ein Beispiel für die Implementierung:
            
            // Initialen Prompt hinzufügen
            messages.add(UserMessage.of(initialPrompt));
            
            if (debug) System.out.println("[DEBUG] Initialer Prompt: " + initialPrompt);
            
            // Antwort des LLM
            String llmResponse = "";
            
            // Maximale Anzahl von Iterationen (um Endlosschleifen zu vermeiden)
            int maxIterations = 10; // Erhöht für komplexere Aufgaben
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
                return executeTool(toolCall);
            }
            
            // Wenn kein Tool-Aufruf erkannt wurde
            return "Kein Tool-Aufruf erkannt.";
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
            // TODO: Implementiere die Prüfung, ob die Aufgabe abgeschlossen ist
            // Hier ist ein Beispiel für die Implementierung:
            
            // Prüfen, ob die Antwort mit "AUFGABE ABGESCHLOSSEN:" beginnt
            return response.trim().startsWith("AUFGABE ABGESCHLOSSEN:");
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
