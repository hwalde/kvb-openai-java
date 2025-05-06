package de.entwickler.training.exercise04;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;


/**
 * Übung 4: Max Tokens (gpt-4o-mini)
 *
 * Ziel: Lernen, die maximale Länge der generierten Antwort zu steuern.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - maxCompletionTokens im ChatRequest
 *
 * Aufgabe:
 * 1. Implementiere die Methode getResponseWithMaxTokens, die eine Anfrage mit einem bestimmten maxCompletionTokens-Wert sendet
 * 2. Rufe diese Methode mit einem Prompt auf, der eine längere Antwort erzeugen würde
 *    (z.B. "Erkläre kurz das Konzept der Objektorientierung.")
 * 3. Probiere verschiedene maxCompletionTokens-Werte aus (z.B. 10, 20, 50, 100)
 * 4. Beobachte, wie die Antwort bei kleineren maxCompletionTokens-Werten abgeschnitten wird
 *
 * Hinweis: Der maxCompletionTokens-Parameter begrenzt die Anzahl der Tokens in der generierten Antwort,
 * nicht die Gesamtzahl der Tokens in der Konversation.
 */
public class MaxTokens {

    public static void main(String[] args) {
        // Ein Prompt, der eine längere Antwort erzeugen würde
        String prompt = "Erkläre kurz das Konzept der Objektorientierung.";

        // TODO: Rufe die Methode getResponseWithMaxTokens mit verschiedenen maxCompletionTokens-Werten auf
        // Probiere Werte wie 10, 20, 50, 100
        // Gib jedes Ergebnis mit einem Label aus, das den maxCompletionTokens-Wert angibt
        System.out.println("Antwort mit maxCompletionTokens = 10:");
        // Dein Code hier...

        System.out.println("\nAntwort mit maxCompletionTokens = 20:");
        // Dein Code hier...

        System.out.println("\nAntwort mit maxCompletionTokens = 50:");
        // Dein Code hier...

        System.out.println("\nAntwort mit maxCompletionTokens = 100:");
        // Dein Code hier...


        // TODO: Füge eine Notiz hinzu, die die Antworten vergleicht und den Effekt von maxCompletionTokens erklärt
        System.out.println("\nVergleich und Erklärung:");
        // Dein Kommentar hier...

    }

    /**
     * Implementiere eine Methode, die eine Anfrage an die OpenAI API mit dem gegebenen Prompt und maxCompletionTokens sendet
     *
     * @param prompt Der zu sendende Prompt
     * @param maxTokens Die maximale Anzahl der zu generierenden Tokens
     * @return Die Antwort von der API
     */
    private static String getResponseWithMaxTokens(String prompt, int maxTokens) {
        // TODO: Erstelle die Chat-Request-Parameter mit dem gegebenen Prompt und maxCompletionTokens
        // Hinweis: Verwende ChatRequest.builder()...maxCompletionTokens(maxTokens)...


        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Sende die Anfrage an die OpenAI API
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere und gib den Antwortinhalt zurück
        // Hinweis: Verwende chatResponse.firstContent()

        return null; // Ersetze dies durch die tatsächliche Implementierung
    }
}
