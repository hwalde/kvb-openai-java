package de.entwickler.training.exercise38;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.SystemMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Übung 38: RooCode Tool simulieren & Antwort senden (Qwen)
 *
 * Ziel: Analog zum OpenAI Function Calling, das Ergebnis des (simulierten)
 * RooCode-Tool-Aufrufs an das Modell zurückmelden.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Formulierung einer neuen User-Nachricht, die das Ergebnis des Tool-Aufrufs enthält
 * - Beibehaltung des bisherigen Chat-Verlaufs
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Verwende den System-Prompt aus Übung 35-37, der das read_file Tool definiert
 * 3. Sende einen User-Prompt, der das Modell dazu bringen sollte, das Tool aufzurufen
 * 4. Extrahiere den Tool-Aufruf aus der Antwort
 * 5. Simuliere die Ausführung des Tools (hier: gib einen festen Dateiinhalt zurück)
 * 6. Sende eine neue Anfrage mit dem ursprünglichen Verlauf + einer neuen User-Nachricht,
 *    die das Ergebnis des Tool-Aufrufs enthält (z.B. "TOOL_RESULT: Der Inhalt der Datei ist: ...")
 * 7. Gib die finale Antwort des Modells aus
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class RooCodeToolSimulation {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // System-Prompt im RooCode-Stil (wie in Übung 35-37)
        String systemPrompt = """
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

        // User-Prompt, der das Modell dazu bringen sollte, das Tool aufzurufen
        String userPrompt = "Kannst du bitte den Inhalt der Datei 'projekt/readme.md' lesen?";

        // Liste für den Chat-Verlauf
        var messages = new ArrayList<io.github.sashirestela.openai.domain.chat.ChatMessage>();
        messages.add(SystemMessage.of(systemPrompt));
        messages.add(UserMessage.of(userPrompt));

        // TODO: Erstelle die Chat-Request-Parameter mit dem System-Prompt und dem User-Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("/models/qwen3-32b-fp8")
        //         .messages(messages)
        //         .build();

        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell mit RooCode System-Prompt:");
        // Dein Code hier...
        // String modelResponse = chatResponse.firstContent();
        // System.out.println(modelResponse);
        
        // TODO: Extrahiere den Tool-Aufruf aus der Antwort
        // Beispiel:
        // String modelResponse = chatResponse.firstContent();
        // ToolCall toolCall = parseToolCall(modelResponse);
        // 
        // if (toolCall != null && "read_file".equals(toolCall.toolName)) {
        //     String filePath = toolCall.parameters.get("path");
        //     System.out.println("\nExtrahierter Dateipfad: " + filePath);
        //     
        //     // Simuliere die Ausführung des Tools
        //     String fileContent = "# Projekt README\n\nDies ist eine Beispiel-README-Datei für das Projekt.\n\n## Installation\n\n1. Repository klonen\n2. Dependencies installieren\n3. Anwendung starten";
        //     System.out.println("\nSimulierter Dateiinhalt:\n" + fileContent);
        //     
        //     // Füge die Modell-Antwort zum Chat-Verlauf hinzu
        //     messages.add(io.github.sashirestela.openai.domain.chat.message.AssistantMessage.of(modelResponse));
        //     
        //     // Füge eine neue User-Nachricht mit dem Tool-Ergebnis hinzu
        //     String toolResultMessage = "TOOL_RESULT: " + fileContent;
        //     messages.add(UserMessage.of(toolResultMessage));
        //     
        //     // Erstelle eine neue Anfrage mit dem aktualisierten Chat-Verlauf
        //     var secondRequest = ChatRequest.builder()
        //             .model("/models/qwen3-32b-fp8")
        //             .messages(messages)
        //             .build();
        //     
        //     // Sende die zweite Anfrage an die API
        //     var secondResponse = openAIClient.chatCompletions().create(secondRequest).join();
        //     
        //     // Gib die finale Antwort aus
        //     System.out.println("\nFinale Antwort nach Tool-Ausführung:");
        //     System.out.println(secondResponse.firstContent());
        // } else {
        //     System.out.println("\nKein Tool-Aufruf in der Antwort gefunden oder falsches Tool.");
        // }

        System.out.println("\n----------------------------------------\n");

        System.out.println("Extrahierter Tool-Aufruf:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Simulierte Tool-Ausführung:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Finale Antwort nach Tool-Ausführung:");
        // Dein Code hier...
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
