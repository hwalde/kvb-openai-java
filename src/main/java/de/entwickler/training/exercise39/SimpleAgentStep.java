package de.entwickler.training.exercise39;

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
 * Übung 39: Einfacher Agenten-Schritt (Qwen)
 *
 * Ziel: Die Schritte 35 bis 38 zu einem einzigen, abgeschlossenen Zyklus zusammenfassen:
 * Anfrage -> LLM fordert Tool -> Parsen -> Simulieren -> Ergebnis an LLM -> Finale Antwort des LLM.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Orchestrierung der einzelnen Schritte in einer Java-Methode oder Klasse
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Implementiere eine Methode executeAgentStep, die den gesamten Ablauf orchestriert:
 *    a) Senden einer Anfrage an das LLM
 *    b) Parsen der Antwort, um einen Tool-Aufruf zu erkennen
 *    c) Simulieren der Tool-Ausführung
 *    d) Senden des Ergebnisses zurück an das LLM
 *    e) Rückgabe der finalen Antwort
 * 3. Teste die Methode mit einer Anfrage, die ein Tool-Aufruf auslösen sollte
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class SimpleAgentStep {

    // System-Prompt im RooCode-Stil (wie in Übung 35-38)
    private static final String SYSTEM_PROMPT = """
            # Tools
            
            ## read_file
            Description: Liest den Inhalt einer Datei. Verwende dieses Tool, um Dateien zu analysieren.
            Parameters:
            - path: (required) Der Pfad zur Datei, die gelesen werden soll.
            
            Usage:
            <read_file>
            <path>Pfad/zur/datei.txt</path>
            </read_file>
            
            Example:
            <read_file>
            <path>config.json</path>
            </read_file>
            
            # Anweisungen
            
            Du bist ein hilfreicher Assistent, der Dateien lesen kann. Wenn der Benutzer dich bittet,
            eine Datei zu lesen, verwende das read_file Tool. Antworte in deutscher Sprache.
            """;

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        
        // User-Prompt, der das Modell dazu bringen sollte, das Tool aufzurufen
        String userPrompt = "Kannst du bitte den Inhalt der Datei 'projekt/readme.md' lesen?";
        
        // TODO: Rufe die Methode executeAgentStep auf und gib das Ergebnis aus
        // Beispiel:
        // String finalResponse = executeAgentStep(userPrompt);
        // System.out.println("Finale Antwort des Agenten:");
        // System.out.println(finalResponse);
        
        System.out.println("Anfrage an den Agenten:");
        System.out.println(userPrompt);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Finale Antwort des Agenten:");
        // Dein Code hier...
    }
    
    /**
     * Führt einen einzelnen Agenten-Schritt aus:
     * 1. Sendet eine Anfrage an das LLM
     * 2. Parst die Antwort, um einen Tool-Aufruf zu erkennen
     * 3. Simuliert die Tool-Ausführung
     * 4. Sendet das Ergebnis zurück an das LLM
     * 5. Gibt die finale Antwort zurück
     * 
     * @param userPrompt Der Prompt des Benutzers
     * @return Die finale Antwort des LLM nach dem Agenten-Schritt
     */
    public static String executeAgentStep(String userPrompt) {
        // TODO: Implementiere die Methode
        // Hier ist ein Beispiel für die Implementierung:
        
        // 1. OpenAI-Client initialisieren
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        
        // 2. Chat-Verlauf initialisieren
        List<io.github.sashirestela.openai.domain.chat.ChatMessage> messages = new ArrayList<>();
        messages.add(SystemMessage.of(SYSTEM_PROMPT));
        messages.add(UserMessage.of(userPrompt));
        
        // 3. Erste Anfrage an das LLM senden
        var chatRequest = ChatRequest.builder()
                .model("/models/qwen3-32b-fp8")
                .messages(messages)
                .build();
        
        var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
        String modelResponse = chatResponse.firstContent();
        
        // 4. Antwort parsen, um einen Tool-Aufruf zu erkennen
        ToolCall toolCall = parseToolCall(modelResponse);
        
        // 5. Wenn ein Tool-Aufruf erkannt wurde, Tool ausführen und Ergebnis zurücksenden
        if (toolCall != null && "read_file".equals(toolCall.toolName)) {
            String filePath = toolCall.parameters.get("path");
            
            // Tool-Ausführung simulieren
            String fileContent = simulateReadFile(filePath);
            
            // Modell-Antwort zum Chat-Verlauf hinzufügen
            messages.add(AssistantMessage.of(modelResponse));
            
            // Tool-Ergebnis als neue User-Nachricht hinzufügen
            String toolResultMessage = "TOOL_RESULT: " + fileContent;
            messages.add(UserMessage.of(toolResultMessage));
            
            // Zweite Anfrage mit dem aktualisierten Chat-Verlauf senden
            var secondRequest = ChatRequest.builder()
                    .model("/models/qwen3-32b-fp8")
                    .messages(messages)
                    .build();
            
            var secondResponse = openAIClient.chatCompletions().create(secondRequest).join();
            
            // Finale Antwort zurückgeben
            return secondResponse.firstContent();
        } else {
            // Wenn kein Tool-Aufruf erkannt wurde, die ursprüngliche Antwort zurückgeben
            return modelResponse;
        }
    }
    
    /**
     * Simuliert das Lesen einer Datei
     * 
     * @param filePath Der Pfad zur Datei
     * @return Der simulierte Inhalt der Datei
     */
    private static String simulateReadFile(String filePath) {
        // In einer echten Anwendung würde hier der tatsächliche Dateiinhalt gelesen werden
        if (filePath.contains("readme.md")) {
            return "# Projekt README\n\nDies ist eine Beispiel-README-Datei für das Projekt.\n\n## Installation\n\n1. Repository klonen\n2. Dependencies installieren\n3. Anwendung starten";
        } else if (filePath.contains("config")) {
            return "{\n  \"name\": \"Beispiel-Projekt\",\n  \"version\": \"1.0.0\",\n  \"description\": \"Ein Beispielprojekt\"\n}";
        } else {
            return "Datei nicht gefunden: " + filePath;
        }
    }
    
    /**
     * Helfer-Klasse zur Repräsentation eines Tool-Aufrufs
     */
    static class ToolCall {
        String toolName;
        Map<String, String> parameters = new HashMap<>();
    }
    
    /**
     * Parst einen Tool-Aufruf aus der Antwort des Modells
     * 
     * @param response Die Antwort des Modells
     * @return Ein ToolCall-Objekt oder null, wenn kein Tool-Aufruf gefunden wurde
     */
    static ToolCall parseToolCall(String response) {
        // Regex-Pattern für den Tool-Aufruf
        // Sucht nach <tool_name>...</tool_name> und extrahiert den Tool-Namen
        Pattern toolPattern = Pattern.compile("<([a-zA-Z_]+)>(.*?)</\\1>", Pattern.DOTALL);
        Matcher toolMatcher = toolPattern.matcher(response);
        
        if (toolMatcher.find()) {
            ToolCall toolCall = new ToolCall();
            toolCall.toolName = toolMatcher.group(1);
            String toolContent = toolMatcher.group(2);
            
            // Regex-Pattern für die Parameter
            // Sucht nach <param_name>...</param_name> und extrahiert den Parameter-Namen und -Wert
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
