package de.entwickler.training.exercise42;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.AssistantMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.SystemMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Übung 42: Agent mit Tool-Auswahl (Mistral Large)
 *
 * Ziel: Den Agenten erweitern, sodass er aus mehreren verfügbaren Tools das passende
 * für die gegebene Aufgabe auswählen kann.
 *
 * Modell: Mistral Large (kann gut mit komplexeren Anweisungen umgehen)
 *
 * Wichtige Parameter/Konzepte:
 * - Definition von mindestens zwei unterschiedlichen Tools im System-Prompt
 * - Logik im Agenten, die den vom LLM angeforderten Toolnamen erkennt und die
 *   entsprechende Simulation ausführt
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Erweitere den Agenten aus Übung 41 um ein zweites Tool (get_current_time)
 * 3. Implementiere die Logik zur Erkennung und Ausführung beider Tools
 * 4. Teste den Agenten mit verschiedenen Aufgaben, die jeweils ein anderes Tool erfordern
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class AgentWithToolSelection {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai03-abn-lan:8080/v1
        // api_key=none
        
        // Erstelle eine Instanz des Agenten
        Agent agent = new Agent();
        
        // Teste den Agenten mit verschiedenen Aufgaben
        
        // Aufgabe 1: Dateien auflisten (sollte list_files verwenden)
        String prompt1 = "Liste die Dateien im Verzeichnis 'src' auf.";
        System.out.println("Aufgabe 1: " + prompt1);
        String response1 = agent.runTask(prompt1);
        System.out.println("Antwort: " + response1);
        
        System.out.println("\n----------------------------------------\n");
        
        // Aufgabe 2: Aktuelle Uhrzeit abfragen (sollte get_current_time verwenden)
        String prompt2 = "Wie spät ist es jetzt?";
        System.out.println("Aufgabe 2: " + prompt2);
        String response2 = agent.runTask(prompt2);
        System.out.println("Antwort: " + response2);
        
        System.out.println("\n----------------------------------------\n");
        
        // Optional: Aufgabe 3: Kombinierte Anfrage (könnte beide Tools verwenden)
        String prompt3 = "Wie spät ist es und welche Dateien gibt es im Verzeichnis 'src'?";
        System.out.println("Aufgabe 3: " + prompt3);
        String response3 = agent.runTask(prompt3);
        System.out.println("Antwort: " + response3);
    }
    
    /**
     * Agent-Klasse, die mit dem LLM interagiert und verschiedene Tools verwendet, um Aufgaben zu lösen
     */
    static class Agent {
        // System-Prompt im RooCode-Stil mit zwei Tools
        private static final String SYSTEM_PROMPT = """
                # Tools
                
                ## list_files
                Description: Listet Dateien in einem Verzeichnis auf.
                Parameters:
                - path: (required) Der Pfad zum Verzeichnis, dessen Inhalt aufgelistet werden soll.
                
                Usage:
                <list_files>
                <path>Pfad/zum/verzeichnis</path>
                </list_files>
                
                Example:
                <list_files>
                <path>src</path>
                </list_files>
                
                ## get_current_time
                Description: Gibt die aktuelle Uhrzeit zurück.
                Parameters: Keine
                
                Usage:
                <get_current_time>
                </get_current_time>
                
                Example:
                <get_current_time>
                </get_current_time>
                
                # Anweisungen
                
                Du bist ein hilfreicher Assistent, der verschiedene Tools verwenden kann:
                - Wenn der Benutzer dich bittet, Dateien in einem Verzeichnis aufzulisten, verwende das list_files Tool.
                - Wenn der Benutzer nach der aktuellen Uhrzeit fragt, verwende das get_current_time Tool.
                Antworte in deutscher Sprache.
                Wenn du eine Aufgabe abgeschlossen hast, beginne deine Antwort mit "AUFGABE ABGESCHLOSSEN:".
                """;
        
        // Chat-Verlauf
        private List<io.github.sashirestela.openai.domain.chat.ChatMessage> messages;
        
        // OpenAI-Client
        private final io.github.sashirestela.openai.SimpleOpenAI openAIClient;
        
        // Modell-ID
        private final String modelId = "/models/Mistral-Large-Instruct-2411-FP8";
        
        // Debug-Modus (für detaillierte Ausgaben)
        private final boolean debug = false;
        
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
            // Konversation zurücksetzen für jede neue Aufgabe
            resetConversation();
            
            // Initialen Prompt hinzufügen
            messages.add(UserMessage.of(initialPrompt));
            
            if (debug) System.out.println("[DEBUG] Initialer Prompt: " + initialPrompt);
            
            // Antwort des LLM
            String llmResponse = "";
            
            // Maximale Anzahl von Iterationen (um Endlosschleifen zu vermeiden)
            int maxIterations = 5;
            int iterations = 0;
            
            // Agenten-Schleife
            while (iterations < maxIterations) {
                iterations++;
                
                if (debug) System.out.println("[DEBUG] Iteration " + iterations);
                
                // TODO: Anfrage an das LLM senden
                // Hinweis: Verwende ChatRequest.builder()
                //         .model(modelId)
                //         .messages(messages)
                //         .build();
                
                // TODO: Antwort des LLM zum Chat-Verlauf hinzufügen
                // Hinweis: Verwende messages.add(AssistantMessage.of(llmResponse));
                
                // TODO: Prüfen, ob die Aufgabe abgeschlossen ist
                // Hinweis: Verwende isTaskComplete(llmResponse)
                
                // TODO: Antwort verarbeiten und Tool-Ergebnis als neue User-Nachricht hinzufügen
                // Hinweis: Verwende processLlmResponse(llmResponse) und messages.add(UserMessage.of("TOOL_RESULT: " + toolResult));
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
            
            if ("list_files".equals(toolCall.toolName)) {
                String path = toolCall.parameters.get("path");
                return simulateListFiles(path);
            } else if ("get_current_time".equals(toolCall.toolName)) {
                return simulateGetCurrentTime();
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
         * Simuliert das Auflisten von Dateien in einem Verzeichnis
         * 
         * @param path Der Pfad zum Verzeichnis
         * @return Eine Liste von Dateien im Verzeichnis
         */
        private String simulateListFiles(String path) {
            // In einer echten Anwendung würde hier der tatsächliche Verzeichnisinhalt gelesen werden
            if ("src".equals(path)) {
                return "main\ntest\nREADME.md";
            } else if ("src/main".equals(path)) {
                return "java\nresources";
            } else if ("src/test".equals(path)) {
                return "java\nresources";
            } else if ("src/main/java".equals(path)) {
                return "de\ncom\norg";
            } else {
                return "Verzeichnis nicht gefunden: " + path;
            }
        }
        
        /**
         * Simuliert das Abrufen der aktuellen Uhrzeit
         * 
         * @return Die aktuelle Uhrzeit
         */
        private String simulateGetCurrentTime() {
            // In einer echten Anwendung würde hier die tatsächliche Uhrzeit abgerufen werden
            LocalDateTime now = LocalDateTime.now();
            return now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
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
