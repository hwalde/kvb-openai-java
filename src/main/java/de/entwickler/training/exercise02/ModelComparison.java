package de.entwickler.training.exercise02;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

/**
 * Übung 2: Modellvergleich (gpt-3.5-turbo)
 *
 * Ziel: Den Einfluss der Modellwahl auf die Antwortqualität verstehen.
 * Denselben Prompt an ein älteres, weniger leistungsfähiges Modell senden.
 *
 * Modell: gpt-3.5-turbo (im Vergleich zu gpt-4o-mini aus Übung 1)
 *
 * Wichtige Parameter/Konzepte:
 * - Ändern der Model-ID im ChatRequest
 *
 * Aufgabe:
 * 1. Erstelle ein ChatRequest-Objekt mit demselben Prompt wie in Übung 1 ("Hallo Welt!")
 * 2. Setze das Modell auf gpt-3.5-turbo
 * 3. Sende die Anfrage an die OpenAI API
 * 4. Extrahiere und gib die Antwort aus
 * 5. Vergleiche die Antwort mit der aus Übung 1
 *
 * Hinweis: Beobachte Unterschiede in Stil, Länge oder Inhalt der Antwort.
 * Beachte, dass gpt-3.5-turbo älter ist und Funktionen wie nativen JSON-Modus
 * oder Function Calling nicht unterstützt.
 */
public class ModelComparison {

    public static void main(String[] args) {
        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt "Hallo Welt!" und dem Modell gpt-3.5-turbo
        // Hinweis: Verwende ChatRequest.builder() und setze model auf "gpt-3.5-turbo"


        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Sende die Anfrage an die OpenAI API
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere und gib die Antwort aus
        // Hinweis: Vergiss nicht, ein Label anzugeben, das anzeigt, dass dies von gpt-3.5-turbo stammt


        // TODO: Gib zusätzliche Informationen über die Antwort aus (optional)
        // Wie Modell, Beendigungsgrund, Token-Nutzung


        // TODO: Füge eine Vergleichsnotiz hinzu
        // Hinweis: Fordere den Benutzer auf, diese Antwort mit der aus Übung 1 zu vergleichen

    }
}
