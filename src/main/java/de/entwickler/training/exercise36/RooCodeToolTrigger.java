package de.entwickler.training.exercise36;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.SystemMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

/**
 * Übung 36: RooCode Tool Trigger (Qwen)
 *
 * Ziel: Einen User-Prompt formulieren, der Qwen dazu veranlasst, ein im RooCode-Stil
 * definiertes Tool im erwarteten XML-Format anzufordern.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - User-Prompt, der auf die Funktionalität des definierten Tools abzielt
 * - Senden der Anfrage mit dem System-Prompt aus Übung 35
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Verwende den System-Prompt aus Übung 35, der das read_file Tool definiert
 * 3. Formuliere einen User-Prompt, der das Modell dazu bringen sollte, das Tool aufzurufen
 *    (z.B. "Kannst du bitte den Inhalt der Datei 'projekt/readme.md' lesen?")
 * 4. Sende die Anfrage an das Modell
 * 5. Prüfe, ob das Modell das Tool im erwarteten XML-Format aufruft
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class RooCodeToolTrigger {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // System-Prompt im RooCode-Stil (wie in Übung 35)
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

        // TODO: Formuliere einen User-Prompt, der das Modell dazu bringen sollte, das Tool aufzurufen
        // Beispiel: "Kannst du bitte den Inhalt der Datei 'projekt/readme.md' lesen?"
        String userPrompt = ""; // Dein Prompt hier

        // TODO: Erstelle die Chat-Request-Parameter mit dem System-Prompt und dem User-Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("/models/qwen3-32b-fp8")
        //         .message(SystemMessage.of(systemPrompt))
        //         .message(UserMessage.of(userPrompt))
        //         .build();

        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell mit RooCode System-Prompt:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Analyse der Antwort:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Hat das Modell das Tool im erwarteten XML-Format aufgerufen?
        // - Enthält die Antwort den XML-Block <read_file><path>projekt/readme.md</path></read_file>?
        // - Hat das Modell zusätzlichen Text vor oder nach dem Tool-Aufruf hinzugefügt?
    }
}
