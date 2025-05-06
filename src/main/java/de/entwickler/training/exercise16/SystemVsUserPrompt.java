 
package de.entwickler.training.exercise16;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 16: System vs. User Prompt (Mistral Large)
 * 
 * Ziel: Den Unterschied in der Wirkung untersuchen, wenn eine Anweisung (z.B. eine Persona)
 * im System-Prompt versus im User-Prompt platziert wird.
 * 
 * Modell: Mistral Large
 * 
 * Wichtige Parameter/Konzepte:
 * - Vergleich zweier Anfragen:
 *   a) Persona im System-Prompt, neutrale User-Frage
 *   b) Neutraler oder kein System-Prompt, Persona-Anweisung in der User-Frage
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Erstelle zwei verschiedene Anfragen:
 *    a) Eine Anfrage mit der Persona "Antworte extrem kurz und prägnant" im System-Prompt
 *       und einer neutralen User-Frage
 *    b) Eine Anfrage ohne System-Prompt (oder mit neutralem System-Prompt) und der
 *       Persona-Anweisung in der User-Frage
 * 3. Sende beide Anfragen an das Modell
 * 4. Vergleiche die Antworten und beobachte die Konsistenz und Stärke der Persona-Umsetzung
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class SystemVsUserPrompt {
    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai03-abn-lan:8080/v1
        // api_key=none
        // TODO: Erstelle einen OpenAIClient mit den Konfigurationen für das Mistral Large Modell
        // Hinweis: Du kannst den vorhandenen OpenAIClientService verwenden oder einen neuen Client erstellen
        // Hier ist ein Beispiel, wie du einen neuen Client erstellen könntest:
        // OpenAIClient client = OpenAIOkHttpClient.builder()
        //         .baseUrl("http://kvbai03-abn-lan:8080/v1")
        //         .apiKey("none")
        //         .build();
        // Oder verwende den vorhandenen Client, wenn die config.properties korrekt konfiguriert ist:
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // Persona-Anweisung
        String persona = "Antworte extrem kurz und prägnant";
        // Neutrale User-Frage
        String userQuestion = "Erkläre das Konzept der Vererbung in der objektorientierten Programmierung.";
        // TODO: Erstelle die Chat-Completion-Parameter für die erste Anfrage (Persona im System-Prompt)
        // Hinweis: Verwende ChatRequest.builder()
        //         .addSystemMessage(persona)
        //         .addUserMessage(userQuestion)
        //         .model("/models/Mistral-Large-Instruct-2411-FP8")
        //         .build();
        // TODO: Sende die erste Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf die erste Anfrage aus
        System.out.println("Antwort mit Persona im System-Prompt:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Erstelle die Chat-Completion-Parameter für die zweite Anfrage (Persona im User-Prompt)
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(persona + ": " + userQuestion)
        //         .model("/models/Mistral-Large-Instruct-2411-FP8")
        //         .build();
        // TODO: Sende die zweite Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf die zweite Anfrage aus
        System.out.println("Antwort mit Persona im User-Prompt:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Vergleiche die beiden Antworten hinsichtlich der Konsistenz und Stärke der Persona-Umsetzung
        System.out.println("Vergleich der Antworten:");
        // Dein Kommentar hier...
    }
}
