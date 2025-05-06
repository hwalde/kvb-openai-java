 
package de.entwickler.training.exercise25;

import de.entwickler.training.util.OpenAIClientService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Übung 25: Komplexeres JSON (Qwen)
 * 
 * Ziel: Das Qwen-Modell herausfordern, ein verschachteltes JSON-Objekt basierend
 * auf einer Beschreibung zu generieren.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Detaillierte Beschreibung der verschachtelten Struktur im Prompt
 * - Explizite Aufforderung, valides JSON zu generieren
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen Prompt, der eine verschachtelte JSON-Struktur beschreibt
 *    (z.B. "Generiere ein JSON-Objekt für ein Produkt. Es soll 'produktName' (String),
 *    'preis' (Number) und 'hersteller' (Objekt mit 'name' (String) und 'land' (String)) enthalten.
 *    Beispiel: Produkt 'Laptop', Preis 1200.50, Hersteller 'TechCorp' aus 'USA'.
 *    Gib nur valides JSON aus.")
 * 3. Sende die Anfrage an das Modell
 * 4. Überprüfe die Antwort und versuche, sie als JSON zu parsen
 * 5. Greife auf die verschachtelten Eigenschaften zu
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class ComplexJsonQwen {
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
        // Prompt mit Beschreibung einer verschachtelten JSON-Struktur
        String prompt = "Generiere ein JSON-Objekt für ein Produkt. Es soll 'produktName' (String), " +
                "'preis' (Number) und 'hersteller' (Objekt mit 'name' (String) und 'land' (String)) enthalten. " +
                "Beispiel: Produkt 'Laptop', Preis 1200.50, Hersteller 'TechCorp' aus 'USA'. " +
                "Gib nur valides JSON aus.";
        // TODO: Erstelle die Chat-Completion-Parameter für den Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(prompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere die JSON-Antwort
        String jsonResponse = ""; // Ersetze dies durch die tatsächliche Antwort
        System.out.println("JSON-Antwort vom Qwen-3 32B Modell:");
        System.out.println(jsonResponse);
        System.out.println("\n----------------------------------------\n");
        // TODO: Überprüfe die Antwort und versuche, sie als JSON zu parsen
        System.out.println("Überprüfung des JSON-Formats und Zugriff auf verschachtelte Eigenschaften:");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonResponse);
            // Prüfe, ob die erwarteten Felder vorhanden sind
            boolean hasProductName = jsonNode.has("produktName") && jsonNode.get("produktName").isTextual();
            boolean hasPrice = jsonNode.has("preis") && jsonNode.get("preis").isNumber();
            boolean hasManufacturer = jsonNode.has("hersteller") && jsonNode.get("hersteller").isObject();
            System.out.println("Hat 'produktName' (String): " + hasProductName);
            System.out.println("Hat 'preis' (Number): " + hasPrice);
            System.out.println("Hat 'hersteller' (Objekt): " + hasManufacturer);
            if (hasManufacturer) {
                JsonNode manufacturer = jsonNode.get("hersteller");
                boolean hasManufacturerName = manufacturer.has("name") && manufacturer.get("name").isTextual();
                boolean hasManufacturerCountry = manufacturer.has("land") && manufacturer.get("land").isTextual();
                System.out.println("Hersteller hat 'name' (String): " + hasManufacturerName);
                System.out.println("Hersteller hat 'land' (String): " + hasManufacturerCountry);
                if (hasManufacturerName && hasManufacturerCountry) {
                    System.out.println("\nProduktdetails:");
                    System.out.println("Produktname: " + jsonNode.get("produktName").asText());
                    System.out.println("Preis: " + jsonNode.get("preis").asDouble());
                    System.out.println("Hersteller: " + manufacturer.get("name").asText());
                    System.out.println("Land: " + manufacturer.get("land").asText());
                }
            }
            System.out.println("\nStruktur entspricht den Anforderungen: " + 
                    (hasProductName && hasPrice && hasManufacturer));
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen des JSON: " + e.getMessage());
        }
    }
}
