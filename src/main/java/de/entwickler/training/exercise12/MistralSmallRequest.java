 
package de.entwickler.training.exercise12;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 12: Erste Anfrage (Internes Modell - Mistral Small)
 * 
 * Ziel: Erfolgreiche Verbindung und API-Anfrage an das interne Mistral Small Modell.
 * 
 * Modell: Mistral Small (3.1 24B)
 * 
 * Wichtige Parameter/Konzepte:
 * - Erneute Anpassung der Konfiguration für dieses Modell
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Small Modell:
 *    - base_url: "http://kvbai01-abn-lan:8090/v1"
 *    - api_key: "None"
 *    - modelId: "/models/Mistral-Small-3.1-24B-Instruct-2503/"
 * 2. Erstelle einen OpenAIClient mit diesen Konfigurationen
 * 3. Sende eine einfache Anfrage an das Modell
 * 4. Gib die Antwort aus
 * 5. Vergleiche die Antworten der drei internen Modelle (aus Übungen 10, 11 und 12)
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class MistralSmallRequest {
    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8090/v1
        // api_key=None
        // TODO: Erstelle einen OpenAIClient mit den Konfigurationen für das Mistral Small Modell
        // Hinweis: Du kannst den vorhandenen OpenAIClientService verwenden oder einen neuen Client erstellen
        // Hier ist ein Beispiel, wie du einen neuen Client erstellen könntest:
        // OpenAIClient client = OpenAIOkHttpClient.builder()
        //         .baseUrl("http://kvbai01-abn-lan:8090/v1")
        //         .apiKey("None")
        //         .build();
        // Oder verwende den vorhandenen Client, wenn die config.properties korrekt konfiguriert ist:
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // Ein einfacher Test-Prompt (derselbe wie in Übungen 10 und 11)
        String prompt = "Wer bist du?";
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und der Model-ID
        // Hinweis: Verwende die Model-ID "/models/Mistral-Small-3.1-24B-Instruct-2503/"
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Mistral Small Modell:");
        // Dein Code hier...
        // TODO: Füge einen Vergleich der Antworten der drei internen Modelle hinzu
        System.out.println("\nVergleich der Antworten der drei internen Modelle:");
        System.out.println("1. Qwen-3 32B (Übung 10):");
        // Dein Kommentar hier...
        System.out.println("\n2. Mistral Large (Übung 11):");
        // Dein Kommentar hier...
        System.out.println("\n3. Mistral Small (Übung 12):");
        // Dein Kommentar hier...
    }
}
