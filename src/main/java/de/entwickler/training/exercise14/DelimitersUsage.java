 
package de.entwickler.training.exercise14;
import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 14: Trennzeichen verwenden (Mistral Large)
 * 
 * Ziel: Die Verwendung von Trennzeichen zur Strukturierung von Prompts für interne Modelle testen.
 * 
 * Modell: Mistral Large
 * 
 * Wichtige Parameter/Konzepte:
 * - Einsatz von Markern wie ### oder XML-ähnlichen Tags (<anweisung>, <kontext>) zur Gliederung des Prompts
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Erstelle einen Prompt mit klar getrennten Abschnitten für Anweisung und Kontext
 *    (z.B. "### Anweisung ###\nÜbersetze den folgenden Text ins Deutsche.\n### Text ###\nHello world.")
 * 3. Sende die Anfrage an das Modell
 * 4. Gib die Antwort aus und prüfe, ob das Modell die Trennung berücksichtigt
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class DelimitersUsage {
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
        // Prompt mit Trennzeichen
        String promptWithDelimiters = "### Anweisung ###\n" +
                "Übersetze den folgenden Text ins Deutsche.\n" +
                "### Text ###\n" +
                "Hello world.";
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und der Model-ID
        // Hinweis: Verwende die Model-ID "/models/Mistral-Large-Instruct-2411-FP8"
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Mistral Large Modell mit Trennzeichen im Prompt:");
        // Dein Code hier...
        // TODO: Prüfe, ob das Modell die Trennung berücksichtigt hat
        System.out.println("\nPrüfung der Berücksichtigung der Trennzeichen:");
        // Dein Kommentar hier...
        // Optional: Vergleiche mit einem Prompt ohne Trennzeichen
        System.out.println("\nOptional: Vergleich mit einem Prompt ohne Trennzeichen");
        String promptWithoutDelimiters = "Übersetze den folgenden Text ins Deutsche: Hello world.";
        // TODO: Implementiere den Vergleich mit einem Prompt ohne Trennzeichen
    }
}
