 
package de.entwickler.training.exercise13;
import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 13: Format anfordern (Markdown, Qwen)
 * 
 * Ziel: Das interne Qwen-Modell anweisen, strukturierte Antworten mittels Markdown zu generieren.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Formulierung der Formatierungsanweisung im Prompt
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen Prompt, der das Modell anweist, Markdown für die Formatierung zu verwenden
 *    (z.B. "Erkläre die Hauptkomponenten einer Spring Boot Anwendung. Nutze Markdown für Überschriften und Listen.")
 * 3. Sende die Anfrage an das Modell
 * 4. Gib die Antwort aus und überprüfe, ob die Markdown-Formatierung korrekt ist
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class MarkdownFormat {
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
        // Prompt mit Markdown-Formatierungsanweisung
        String prompt = "Erkläre die Hauptkomponenten einer Spring Boot Anwendung. Nutze Markdown für Überschriften und Listen.";
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und der Model-ID
        // Hinweis: Verwende die Model-ID "/models/qwen3-32b-fp8"
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell mit Markdown-Formatierung:");
        // Dein Code hier...
        // TODO: Überprüfe, ob die Ausgabe korrekt formatiert ist
        System.out.println("\nÜberprüfung der Markdown-Formatierung:");
        // Dein Kommentar hier...
        // Hinweis: Überprüfe, ob die Antwort Markdown-Elemente wie # für Überschriften und - oder * für Listen enthält
    }
}
