package de.entwickler.training.exercise08;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;
import io.github.sashirestela.openai.domain.chat.Chat;
/**
 * Übung 8: Ton vorgeben (gpt-4o-mini)
 *
 * Ziel: Steuern des sprachlichen Stils oder der Tonalität der Antwort.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Anweisung zur gewünschten Tonalität im Prompt
 *
 * Aufgabe:
 * 1. Erstelle zwei verschiedene Prompts:
 *    a) Einen einfachen Prompt ohne Tonvorgabe: "Erkläre den Unterschied zwischen einer Klasse und einem Objekt."
 *    b) Einen Prompt mit Tonvorgabe: "Erkläre den Unterschied zwischen einer Klasse und einem Objekt auf eine sehr enthusiastische und motivierende Weise."
 * 2. Sende beide Prompts an die OpenAI API
 * 3. Vergleiche die Antworten und beobachte, wie die Tonvorgabe die Antwort beeinflusst
 *
 * Hinweis: Diese Übung zeigt, wie man durch Anweisungen zur Tonalität
 * den Stil der Antwort beeinflussen kann.
 */
public class ToneSpecification {
    public static void main(String[] args) {
        // Prompt ohne Tonvorgabe
        String simplePrompt = "Erkläre den Unterschied zwischen einer Klasse und einem Objekt.";
        // Prompt mit Tonvorgabe
        String tonePrompt = "Erkläre den Unterschied zwischen einer Klasse und einem Objekt auf eine sehr enthusiastische und motivierende Weise.";
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
        // TODO: Erstelle die Chat-Completion-Parameter für den Prompt mit Tonvorgabe
        // Beispiel:
        // var toneRequest = ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(tonePrompt))
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Beispiel:
        // var toneResponse = openAIClient.chatCompletions().create(toneRequest).join();

        // TODO: Extrahiere und gib die Antwort auf den Prompt mit Tonvorgabe aus
        System.out.println("Antwort auf den Prompt mit Tonvorgabe:");
        // Dein Code hier...
        // Beispiel:
        // System.out.println(toneResponse.firstContent());
        System.out.println("\n----------------------------------------\n");
        // TODO: Füge einen Vergleich der beiden Antworten hinzu
        System.out.println("Vergleich der Antworten:");
        // Dein Kommentar hier...
    }
}
