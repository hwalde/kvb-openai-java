package de.entwickler.training.exercise55;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.net.ConnectException;
import java.util.concurrent.CompletionException;

/**
 * Übung 55: API Fehlerbehandlung
 *
 * Ziel: Robusteren Code schreiben, der mit möglichen Fehlern bei API-Aufrufen
 * (z.B. Netzwerkprobleme, ungültiger Key, Ratenlimits) umgehen kann.
 *
 * Modell: Beliebiges Modell, z.B. Qwen-3 32B oder gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - try-catch-Blöcke um den API-Aufruf
 * - Abfangen spezifischer Exceptions der verwendeten Java-Bibliothek
 * - Sinnvolle Fehlerprotokollierung oder Benutzerfeedback
 *
 * Aufgabe:
 * 1. Implementiere einen API-Aufruf mit umfassender Fehlerbehandlung
 * 2. Fange verschiedene mögliche Exceptions ab und gib sinnvolle Fehlermeldungen aus
 * 3. Optional: Simuliere einen Fehler (z.B. durch temporäres Ändern des API-Keys oder der URL)
 *
 * Hinweis: In einer echten Anwendung würde man Fehler in ein Log schreiben oder
 * dem Benutzer eine hilfreiche Fehlermeldung anzeigen.
 */
public class ApiErrorHandling {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Einfacher Prompt für einen API-Aufruf
        String prompt = "Hallo, wie geht es dir?";

        // TODO: Erstelle die Chat-Request-Parameter
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini") // oder ein anderes Modell deiner Wahl
        //         .message(UserMessage.of(prompt))
        //         .build();

        // TODO: Implementiere einen API-Aufruf mit umfassender Fehlerbehandlung
        // Beispiel:
        // try {
        //     var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
        //     System.out.println("Antwort: " + chatResponse.firstContent());
        // } catch (CompletionException e) {
        //     // CompletionException ist die Wrapper-Exception für asynchrone Aufrufe
        //     Throwable cause = e.getCause();
        //     
        //     if (cause instanceof ConnectException) {
        //         System.err.println("Verbindungsfehler: Konnte keine Verbindung zur API herstellen.");
        //         System.err.println("Bitte überprüfe deine Internetverbindung und die API-URL.");
        //     } else if (cause.getMessage().contains("401")) {
        //         System.err.println("Authentifizierungsfehler: Ungültiger API-Key.");
        //         System.err.println("Bitte überprüfe deinen API-Key in der config.properties Datei.");
        //     } else if (cause.getMessage().contains("429")) {
        //         System.err.println("Rate Limit erreicht: Zu viele Anfragen in kurzer Zeit.");
        //         System.err.println("Bitte warte einen Moment und versuche es dann erneut.");
        //     } else if (cause.getMessage().contains("500")) {
        //         System.err.println("Server-Fehler: Die API hat einen internen Fehler gemeldet.");
        //         System.err.println("Bitte versuche es später erneut.");
        //     } else {
        //         System.err.println("Unerwarteter Fehler: " + cause.getMessage());
        //         cause.printStackTrace();
        //     }
        // } catch (Exception e) {
        //     System.err.println("Allgemeiner Fehler: " + e.getMessage());
        //     e.printStackTrace();
        // }

        System.out.println("API-Aufruf mit Fehlerbehandlung:");
        // Dein Code hier...

        // Optional: Simuliere einen Fehler
        System.out.println("\n----------------------------------------\n");
        System.out.println("Optional: Simulation eines Fehlers");
        
        // Beispiel für die Simulation eines Fehlers:
        // - Kommentiere den folgenden Code ein und passe ihn an, um einen Fehler zu simulieren
        // - Zum Beispiel, indem du ein ungültiges Modell verwendest oder eine ungültige URL
        
        // try {
        //     var invalidRequest = ChatRequest.builder()
        //             .model("nicht-existierendes-modell") // Ungültiges Modell
        //             .message(UserMessage.of(prompt))
        //             .build();
        //     
        //     var chatResponse = openAIClient.chatCompletions().create(invalidRequest).join();
        //     System.out.println("Antwort: " + chatResponse.firstContent());
        // } catch (Exception e) {
        //     System.err.println("Simulierter Fehler: " + e.getMessage());
        // }
    }
}
