package de.entwickler.training.exercise03;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

/**
 * Übung 3: Temperatur-Parameter (gpt-4o-mini)
 *
 * Ziel: Verstehen, wie der temperature-Parameter die Kreativität/Zufälligkeit
 * der Antwort beeinflusst.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - temperature im ChatRequest
 * - Werte: 0.0 (deterministisch) vs. 1.0 (kreativer)
 *
 * Aufgabe:
 * 1. Implementiere die Methode getResponseWithTemperature, die eine Anfrage mit einem bestimmten Temperaturwert sendet
 * 2. Rufe diese Methode zweimal mit demselben Prompt, aber unterschiedlichen Temperaturwerten auf:
 *    - Einmal mit temperature = 0.0 (deterministisch)
 *    - Einmal mit temperature = 1.0 (kreativ)
 * 3. Gib beide Antworten aus und vergleiche sie
 * 4. Führe das Programm mehrmals aus, um die Varianz bei temperature = 1.0 zu beobachten
 *
 * Hinweis: Verwende einen Prompt, der Variationen ermöglicht, z.B.
 * "Schreibe einen kurzen Satz über Programmieren."
 */
public class TemperatureParameter {

    public static void main(String[] args) {
        // Der Prompt, der Variationen ermöglicht
        String prompt = "Schreibe einen kurzen Satz über Programmieren.";

        // TODO: Rufe die Methode getResponseWithTemperature mit temperature = 0.0 auf
        // und gib das Ergebnis aus
        System.out.println("Antwort mit temperature = 0.0 (deterministisch):");
        // Dein Code hier...


        // TODO: Rufe die Methode getResponseWithTemperature mit temperature = 1.0 auf
        // und gib das Ergebnis aus
        System.out.println("\nAntwort mit temperature = 1.0 (kreativ):");
        // Dein Code hier...


        // TODO: Füge eine Notiz zum Vergleich der Antworten hinzu
        System.out.println("\nVergleich:");
        // Dein Kommentar hier...

    }

    /**
     * Implementiere eine Methode, die eine Anfrage an die OpenAI API mit dem gegebenen Prompt und der Temperatur sendet
     *
     * @param prompt Der zu sendende Prompt
     * @param temperature Der Temperaturwert (0.0 bis 1.0)
     * @return Die Antwort von der API
     */
    private static String getResponseWithTemperature(String prompt, double temperature) {
        // TODO: Erstelle die Chat-Request-Parameter mit dem gegebenen Prompt und der Temperatur
        // Hinweis: Verwende ChatRequest.builder()...temperature(temperature)...


        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Sende die Anfrage an die OpenAI API
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere und gib den Antwortinhalt zurück
        // Hinweis: Verwende chatResponse.firstContent()

        return null; // Ersetze dies durch die tatsächliche Implementierung
    }
}
