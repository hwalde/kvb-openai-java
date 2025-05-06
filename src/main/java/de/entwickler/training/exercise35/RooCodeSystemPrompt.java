package de.entwickler.training.exercise35;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.SystemMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

/**
 * Übung 35: RooCode System Prompt (Qwen)
 *
 * Ziel: Einen System-Prompt für Qwen erstellen, der Tools im XML-Format beschreibt,
 * wie im RooCode-Beispiel gezeigt.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Strukturierung des System-Prompts mit <tool_name>, <description>, <parameters>,
 *   <usage>, <example> für jedes Tool unter einem # Tools-Abschnitt
 * - Verwendung von XML-Tags zur Beschreibung
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen System-Prompt im RooCode-Stil, der ein einfaches Tool beschreibt
 *    (z.B. read_file mit einem path-Parameter)
 * 3. Sende eine einfache Anfrage an das Modell mit diesem System-Prompt
 * 4. Beobachte, ob das Modell den System-Prompt versteht und entsprechend reagiert
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 * 
 * Hinweis: Schau dir zuvor unbedingt Roos System Prompt einmal an, damit du weißt, wie er aufgebaut ist.
 * Diesen findest du in der Datei roo_system_prompt.md.
 */
public class RooCodeSystemPrompt {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Erstelle einen System-Prompt im RooCode-Stil
        // Beispiel:
        // String systemPrompt = """
        //         # Tools
        //         
        //         ## read_file
        //         Description: Liest den Inhalt einer Datei. Verwende dieses Tool, um Dateien zu analysieren.
        //         Parameters:
        //         - path: (required) Der Pfad zur Datei, die gelesen werden soll.
        //         
        //         Usage:
        //         <read_file>
        //         <path>Pfad/zur/datei.txt</path>
        //         </read_file>
        //         
        //         Example:
        //         <read_file>
        //         <path>config.json</path>
        //         </read_file>
        //         
        //         # Anweisungen
        //         
        //         Du bist ein hilfreicher Assistent, der Dateien lesen kann. Wenn der Benutzer dich bittet,
        //         eine Datei zu lesen, verwende das read_file Tool. Antworte in deutscher Sprache.
        //         """;

        // Ein einfacher User-Prompt, der nicht direkt nach einem Tool-Aufruf fragt
        String userPrompt = "Hallo! Wie kann ich dich bitten, eine Datei zu lesen?";

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
        // - Hat das Modell den System-Prompt verstanden?
        // - Hat es auf die Frage geantwortet, ohne direkt ein Tool aufzurufen?
        // - Hat es erklärt, wie man das Tool verwenden kann?
    }
}
