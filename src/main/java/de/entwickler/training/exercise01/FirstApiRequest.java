package de.entwickler.training.exercise01;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;


/**
 * Übung 1: Erste API-Anfrage (gpt-4o-mini)
 *
 * Ziel: Vertraut werden mit dem grundlegenden Ablauf einer API-Anfrage an ein OpenAI-Modell
 * über die Java-Bibliothek. Eine einfache Anfrage senden und die Antwort empfangen/ausgeben.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - API-Client-Initialisierung
 * - Chat-Completion-Endpunkt
 * - ChatRequest
 * - UserMessage (Rolle "user")
 * - Model-ID
 * - Antwort-Objekt (ChatResponse)
 *
 * Aufgabe:
 * 1. Erstelle ein ChatRequest-Objekt mit dem Prompt "Hallo Welt!"
 * 2. Setze das Modell auf gpt-4o-mini
 * 3. Sende die Anfrage an die OpenAI API
 * 4. Extrahiere und gib die Antwort aus
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key in config.properties korrekt konfiguriert ist.
 */
public class FirstApiRequest {

    public static void main(String[] args) {
        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt "Hallo Welt!" und dem Modell gpt-4o-mini
        // Hinweis: Verwende ChatRequest.builder()...


        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Sende die Anfrage an die OpenAI API
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere den Antwortinhalt aus der ChatResponse
        // Hinweis: Verwende chatResponse.firstContent()


        // TODO: Gib die Antwort aus
        // Bonus: Gib zusätzliche Informationen wie Modell, Beendigungsgrund und Token-Nutzung aus

    }
}
