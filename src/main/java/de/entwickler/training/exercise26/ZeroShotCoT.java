
package de.entwickler.training.exercise26;
import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 26: Zero-Shot CoT (Qwen)
 * 
 * Ziel: Die interne "Denkfähigkeit" von Qwen durch eine einfache Anweisung
 * ("Denke Schritt für Schritt") anregen, um die Nachvollziehbarkeit bei einfachen
 * Problemen zu erhöhen.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Hinzufügen der Phrase "Denke Schritt für Schritt" oder "Let's think step by step" zum Prompt
 * - Analyse der vollständigen Antwort (inklusive der Denkschritte)
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle zwei verschiedene Prompts für dasselbe Problem:
 *    a) Einen einfachen Prompt ohne CoT-Anweisung: "Ein Apfel kostet 0.50€, eine Banane 0.30€. Was kosten 3 Äpfel und 2 Bananen?"
 *    b) Einen Prompt mit CoT-Anweisung: "Ein Apfel kostet 0.50€, eine Banane 0.30€. Was kosten 3 Äpfel und 2 Bananen? Denke Schritt für Schritt."
 * 3. Sende beide Prompts an das Modell
 * 4. Vergleiche die Antworten und beobachte, ob das Modell bei der CoT-Anweisung die einzelnen Rechenschritte aufführt
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class ZeroShotCoT {
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
        // Prompt ohne CoT-Anweisung
        String simplePrompt = "Ein Apfel kostet 0.50€, eine Banane 0.30€. Was kosten 3 Äpfel und 2 Bananen?";
        // Prompt mit CoT-Anweisung
        String cotPrompt = "Ein Apfel kostet 0.50€, eine Banane 0.30€. Was kosten 3 Äpfel und 2 Bananen? Denke Schritt für Schritt.";
        // TODO: Erstelle die Chat-Completion-Parameter für den einfachen Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(simplePrompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage mit dem einfachen Prompt an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf den einfachen Prompt aus
        System.out.println("Antwort auf den einfachen Prompt:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Erstelle die Chat-Completion-Parameter für den Prompt mit CoT-Anweisung
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(cotPrompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage mit dem CoT-Prompt an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf den CoT-Prompt aus
        System.out.println("Antwort auf den Prompt mit CoT-Anweisung:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Vergleiche die Antworten und beobachte, ob das Modell bei der CoT-Anweisung die einzelnen Rechenschritte aufführt
        System.out.println("Vergleich der Antworten:");
        // Dein Kommentar hier...
    }
}
