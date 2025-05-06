 
package de.entwickler.training.exercise22;

import de.entwickler.training.util.OpenAIClientService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Übung 22: JSON Mode anfordern (Qwen)
 * 
 * Ziel: Versuchen, das interne Qwen-Modell zur Ausgabe von JSON zu bewegen,
 * auch wenn es keinen dedizierten API-Parameter dafür gibt.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Explizite Anweisung im Prompt, JSON zu generieren
 * - Ggf. Vorgabe der Struktur im Prompt
 * - Parsing der (potenziell nicht immer validen) Textantwort
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen Prompt, der das Modell explizit anweist, JSON zu generieren
 *    (z.B. "Gib die folgende Information als JSON-Objekt aus: Name ist Anna, Alter ist 30.
 *    Stelle sicher, dass die Ausgabe ausschließlich valides JSON ist.")
 * 3. Sende die Anfrage an das Modell
 * 4. Überprüfe die Antwort und versuche, sie als JSON zu parsen
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class JsonModeQwen {
    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        // TODO: Erstelle einen OpenAIClient mit den Konfigurationen für das Qwen-3 32B Modell
        // Hinweis: Du kannst den vorhandenen OpenAIClientService verwenden oder einen neuen Client erstellen
        // Hier ist ein Beispiel, wie du einen neuen Client erstellen könntest:
        // OpenAIClient client = OpenAIOkHttpClient.builder()
        //         .baseUrl("http://kvbai01-abn-lan:8080/v1")
        //         .apiKey("NONE")
        //         .build();
        // Oder verwende den vorhandenen Client, wenn die config.properties korrekt konfiguriert ist:
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // Prompt mit expliziter Anweisung, JSON zu generieren
        String prompt = "Gib die folgende Information als JSON-Objekt aus: Name ist Anna, Alter ist 30. " +
                "Stelle sicher, dass die Ausgabe ausschließlich valides JSON ist.";
        // TODO: Erstelle die Chat-Completion-Parameter für den Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(prompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell mit JSON-Anweisung:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Überprüfe die Antwort und versuche, sie als JSON zu parsen
        System.out.println("Überprüfung des JSON-Formats:");
        // Dein Code hier...
        // Hier ist eine Hilfsmethode zum Parsen und Validieren von JSON
        // Du kannst sie verwenden, um die Antwort zu überprüfen
        // String jsonString = "..."; // Die Antwort vom Modell
        // boolean isValidJson = isValidJson(jsonString);
        // System.out.println("Ist valides JSON: " + isValidJson);
    }
    /**
     * Hilfsmethode zum Überprüfen, ob ein String valides JSON ist
     * 
     * @param jsonString Der zu überprüfende JSON-String
     * @return true, wenn der String valides JSON ist, sonst false
     */
    private static boolean isValidJson(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            return true;
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen des JSON: " + e.getMessage());
            return false;
        }
    }
}
