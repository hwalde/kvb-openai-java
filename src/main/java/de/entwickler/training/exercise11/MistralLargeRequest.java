 
package de.entwickler.training.exercise11;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 11: Erste Anfrage (Internes Modell - Mistral Large)
 * 
 * Ziel: Erfolgreiche Verbindung und API-Anfrage an das interne Mistral Large Modell.
 * 
 * Modell: Mistral Large (Instruct 2411)
 * 
 * Wichtige Parameter/Konzepte:
 * - Anpassung der Konfiguration (base_url, api_key, modelId) für dieses spezifische Modell
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell:
 *    - base_url: "http://kvbai03-abn-lan:8080/v1"
 *    - api_key: "none"
 *    - modelId: "/models/Mistral-Large-Instruct-2411-FP8"
 * 2. Erstelle einen OpenAIClient mit diesen Konfigurationen
 * 3. Sende eine einfache Anfrage an das Modell
 * 4. Gib die Antwort aus
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class MistralLargeRequest {
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
        // Ein einfacher Test-Prompt (derselbe wie in Übung 10)
        String prompt = "Wer bist du?";
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und der Model-ID
        // Hinweis: Verwende die Model-ID "/models/Mistral-Large-Instruct-2411-FP8"
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Mistral Large Modell:");
        // Dein Code hier...
        // TODO: Füge einen Kommentar hinzu, der die Antwort analysiert
        System.out.println("\nAnalyse der Antwort:");
        // Dein Kommentar hier...
    }
}
