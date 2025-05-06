package de.entwickler.training.exercise09;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;
import io.github.sashirestela.openai.domain.chat.Chat;
/**
 * Übung 9: Format anfordern (Liste, gpt-4o-mini)
 *
 * Ziel: Das Modell anweisen, die Ausgabe in einem bestimmten einfachen Format
 * (hier: nummerierte Liste) zu strukturieren.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Explizite Formatierungsanweisung im Prompt
 *
 * Aufgabe:
 * 1. Erstelle zwei verschiedene Prompts:
 *    a) Einen einfachen Prompt ohne Formatvorgabe: "Nenne drei Vorteile von Unit-Tests."
 *    b) Einen Prompt mit Formatvorgabe: "Nenne drei Vorteile von Unit-Tests. Gib die Antwort als nummerierte Liste aus."
 * 2. Sende beide Prompts an die OpenAI API
 * 3. Vergleiche die Antworten und beobachte, wie die Formatvorgabe die Antwort beeinflusst
 *
 * Hinweis: Diese Übung zeigt, wie man durch explizite Anweisungen
 * das Format der Antwort steuern kann.
 */
public class FormatRequest {
    public static void main(String[] args) {
        // Prompt ohne Formatvorgabe
        String simplePrompt = "Nenne drei Vorteile von Unit-Tests.";
        // Prompt mit Formatvorgabe
        String formatPrompt = "Nenne drei Vorteile von Unit-Tests. Gib die Antwort als nummerierte Liste aus.";
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // TODO: Erstelle die Chat-Completion-Parameter für den einfachen Prompt
        // Hinweis: Verwende ChatRequest.builder()...
        // Beispiel:
        // var chatRequest = ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(simplePrompt))
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Beispiel:
        // var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort auf den einfachen Prompt aus
        System.out.println("Antwort auf den einfachen Prompt:");
        // Dein Code hier...
        // Beispiel:
        // System.out.println(chatResponse.firstContent());
        System.out.println("\n----------------------------------------\n");
        // TODO: Erstelle die Chat-Completion-Parameter für den Prompt mit Formatvorgabe
        // Beispiel:
        // var formatRequest = ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(formatPrompt))
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Beispiel:
        // var formatResponse = openAIClient.chatCompletions().create(formatRequest).join();

        // TODO: Extrahiere und gib die Antwort auf den Prompt mit Formatvorgabe aus
        System.out.println("Antwort auf den Prompt mit Formatvorgabe:");
        // Dein Code hier...
        // Beispiel:
        // System.out.println(formatResponse.firstContent());
        System.out.println("\n----------------------------------------\n");
        // TODO: Füge einen Vergleich der beiden Antworten hinzu
        System.out.println("Vergleich der Antworten:");
        // Dein Kommentar hier...
    }
}
