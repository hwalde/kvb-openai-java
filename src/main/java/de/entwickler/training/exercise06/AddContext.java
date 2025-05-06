package de.entwickler.training.exercise06;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;
import io.github.sashirestela.openai.domain.chat.Chat;


/**
 * Übung 6: Kontext hinzufügen (gpt-4o-mini)
 *
 * Ziel: Verstehen, wie zusätzlicher Kontext im Prompt die Relevanz und Qualität
 * der Antwort verbessern kann.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Bereitstellung relevanter Hintergrundinformationen zusammen mit der Anweisung
 *
 * Aufgabe:
 * 1. Erstelle zwei verschiedene Prompts:
 *    a) Einen einfachen Prompt ohne Kontext: "Erkläre 'Refactoring'."
 *    b) Einen Prompt mit Kontext: "Erkläre 'Refactoring' im Kontext der Java-Softwareentwicklung für einen erfahrenen Entwickler."
 * 2. Sende beide Prompts an die OpenAI API
 * 3. Vergleiche die Antworten und beobachte, wie der zusätzliche Kontext die Antwort beeinflusst
 *
 * Hinweis: Diese Übung zeigt, wie wichtig es ist, dem Modell ausreichend Kontext zu geben,
 * damit es relevante und qualitativ hochwertige Antworten liefern kann.
 */
public class AddContext {

    public static void main(String[] args) {
        // Prompt ohne Kontext
        String simplePrompt = "Erkläre 'Refactoring'.";

        // Prompt mit Kontext
        String contextPrompt = "Erkläre 'Refactoring' im Kontext der Java-Softwareentwicklung für einen erfahrenen Entwickler.";

        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Erstelle die Chat-Request-Parameter für den einfachen Prompt
        // Hinweis: Verwende ChatRequest.builder()...
        // Beispiel:
        // var chatRequest = ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(simplePrompt))
        //         .build();


        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();
        // Beispiel:
        // var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere und gib die Antwort auf den einfachen Prompt aus
        System.out.println("Antwort auf den einfachen Prompt:");
        // Dein Code hier...
        // Beispiel:
        // System.out.println(chatResponse.firstContent());

        System.out.println("\n----------------------------------------\n");

        // TODO: Erstelle die Chat-Request-Parameter für den Prompt mit Kontext


        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort


        // TODO: Extrahiere und gib die Antwort auf den Prompt mit Kontext aus
        System.out.println("Antwort auf den Prompt mit Kontext:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        // TODO: Füge einen Vergleich der beiden Antworten hinzu
        System.out.println("Vergleich der Antworten:");
        // Dein Kommentar hier...
    }
}
