package de.entwickler.training.exercise37;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Übung 37: RooCode XML parsen (mit Helfer)
 *
 * Ziel: Die XML-Antwort des Modells, die einen Tool-Aufruf enthält, mithilfe eines
 * bereitgestellten (oder selbst geschriebenen) einfachen XML-Parsers verarbeiten.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Identifizieren des XML-Blocks in der Modellantwort
 * - Extrahieren des Tool-Namens und der Parameterwerte
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Verwende den System-Prompt aus Übung 35/36, der das read_file Tool definiert
 * 3. Sende einen User-Prompt, der das Modell dazu bringen sollte, das Tool aufzurufen
 * 4. Verwende den bereitgestellten XML-Parser-Helfer, um den Tool-Namen und den Wert
 *    des path-Parameters aus der Antwort zu extrahieren
 * 5. Gib die extrahierten Informationen aus
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class RooCodeXmlParser {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // System-Prompt im RooCode-Stil (wie in Übung 35/36)
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

        // TODO: Erstelle die Chat-Request-Parameter mit dem System-Prompt und dem User-Prompt
         ChatRequest.builder()
                 .model("/models/qwen3-32b-fp8")
                 .message(ChatMessage.SystemMessage.of(systemPrompt))
                 .message(ChatMessage.UserMessage.of(userPrompt))
                 .build();

        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell mit RooCode System-Prompt:");
        // Dein Code hier...
        // String modelResponse = chatResponse.firstContent();
        // System.out.println(modelResponse);

        System.out.println("\n----------------------------------------\n");

        // TODO: Verwende den XML-Parser-Helfer, um den Tool-Namen und den Wert des path-Parameters zu extrahieren
        // Beispiel:
        // String modelResponse = chatResponse.firstContent();
        // ToolCall toolCall = parseToolCall(modelResponse);
        // 
        // if (toolCall != null) {
        //     System.out.println("Extrahierte Tool-Informationen:");
        //     System.out.println("Tool-Name: " + toolCall.toolName);
        //     System.out.println("Parameter 'path': " + toolCall.parameters.get("path"));
        // } else {
        //     System.out.println("Kein Tool-Aufruf in der Antwort gefunden.");
        // }

        System.out.println("Extrahierte Tool-Informationen:");
        // Dein Code hier...
    }

    /**
     * Helfer-Klasse zur Repräsentation eines Tool-Aufrufs
     */
    static class ToolCall {
        String toolName;
        java.util.Map<String, String> parameters = new java.util.HashMap<>();
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
