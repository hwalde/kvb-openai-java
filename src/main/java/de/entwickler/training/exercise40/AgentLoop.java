package de.entwickler.training.exercise40;

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
 * Übung 40: Agenten-Loop (Grundstruktur)
 *
 * Ziel: Das Grundgerüst einer Agenten-Schleife in Java entwerfen, die wiederholt
 * mit dem LLM interagiert, bis eine Aufgabe abgeschlossen ist.
 *
 * Modell: (Theoretisch, Implementierung mit Qwen oder Mistral Large in folgenden Schritten)
 *
 * Wichtige Parameter/Konzepte:
 * - Klassenstruktur (z.B. Agent-Klasse)
 * - Schleifenlogik (while oder do-while)
 * - Zustandsverwaltung (aktueller Chat-Verlauf)
 * - Logik zur Unterscheidung zwischen normaler Textantwort und Tool-Aufruf-Anforderung
 *
 * Aufgabe:
 * 1. Entwerfe eine Agent-Klasse mit den folgenden Methoden:
 *    - runTask(initialPrompt): Führt die Agenten-Schleife aus
 *    - processLlmResponse(response): Verarbeitet die Antwort des LLM
 *    - executeTool(toolCall): Führt ein Tool aus
 *    - isTaskComplete(response): Prüft, ob die Aufgabe abgeschlossen ist
 * 2. Implementiere die Grundstruktur der Agenten-Schleife in der runTask-Methode
 * 3. Implementiere die Hilfsmethoden (können zunächst Platzhalter sein)
 *
 * Hinweis: In dieser Übung geht es um die Struktur des Agenten, nicht um die
 * vollständige Implementierung. Die tatsächliche Funktionalität wird in den
 * folgenden Übungen hinzugefügt.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class AgentLoop {

    public static void main(String[] args) {
        // Erstelle eine Instanz des Agenten
        Agent agent = new Agent();
        
        // Führe eine Aufgabe aus
        String initialPrompt = "Kannst du mir helfen, die Dateien im Verzeichnis 'src' aufzulisten?";
        String finalResponse = agent.runTask(initialPrompt);
        
        System.out.println("Anfrage an den Agenten:");
        System.out.println(initialPrompt);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Finale Antwort des Agenten:");
        System.out.println(finalResponse);
    }
    
    /**
     * Agent-Klasse, die wiederholt mit dem LLM interagiert, bis eine Aufgabe abgeschlossen ist
     */
    static class Agent {
        // System-Prompt im RooCode-Stil
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
                
                # Anweisungen
                
                Du bist ein hilfreicher Assistent, der Dateien auflisten kann. Wenn der Benutzer dich bittet,
                Dateien in einem Verzeichnis aufzulisten, verwende das list_files Tool. Antworte in deutscher Sprache.
                Wenn du eine Aufgabe abgeschlossen hast, beginne deine Antwort mit "AUFGABE ABGESCHLOSSEN:".
                """;
        
        // Chat-Verlauf
        private List<io.github.sashirestela.openai.domain.chat.ChatMessage> messages;
        
        // OpenAI-Client
        private final io.github.sashirestela.openai.SimpleOpenAI openAIClient;
        
        // Modell-ID
        private final String modelId = "/models/qwen3-32b-fp8";
        
        /**
         * Konstruktor
         */
        public Agent() {
            this.openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
            this.messages = new ArrayList<>();
            this.messages.add(SystemMessage.of(SYSTEM_PROMPT));
        }
        
        /**
         * Führt eine Aufgabe aus, indem wiederholt mit dem LLM interagiert wird,
         * bis die Aufgabe abgeschlossen ist
         * 
         * @param initialPrompt Der initiale Prompt des Benutzers
         * @return Die finale Antwort des LLM
         */
        public String runTask(String initialPrompt) {
            // TODO: Implementiere die Agenten-Schleife
            // Hier ist ein Beispiel für die Implementierung:
            
            // Initialen Prompt hinzufügen
            messages.add(UserMessage.of(initialPrompt));
            
            // Antwort des LLM
            String llmResponse = "";
            
            // Maximale Anzahl von Iterationen (um Endlosschleifen zu vermeiden)
            int maxIterations = 5;
            int iterations = 0;
            
            // Agenten-Schleife
            while (iterations < maxIterations) {
                iterations++;
                
                // Anfrage an das LLM senden
                var chatRequest = ChatRequest.builder()
                        .model(modelId)
                        .messages(messages)
                        .build();
                
                var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
                llmResponse = chatResponse.firstContent();
                
                // Antwort des LLM zum Chat-Verlauf hinzufügen
                messages.add(AssistantMessage.of(llmResponse));
                
                // Prüfen, ob die Aufgabe abgeschlossen ist
                if (isTaskComplete(llmResponse)) {
                    return llmResponse;
                }
                
                // Antwort verarbeiten
                String toolResult = processLlmResponse(llmResponse);
                
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
            // TODO: Implementiere die Tool-Ausführung
            // Hier ist ein Beispiel für die Implementierung:
            
            if ("list_files".equals(toolCall.toolName)) {
                String path = toolCall.parameters.get("path");
                return simulateListFiles(path);
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
            } else {
                return "Verzeichnis nicht gefunden: " + path;
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
