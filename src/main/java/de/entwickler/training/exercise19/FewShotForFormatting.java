 
package de.entwickler.training.exercise19;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 19: Few-Shot für Formatierung (Qwen)
 * 
 * Ziel: Dem Qwen-Modell durch Beispiele ein spezifisches, möglicherweise komplexes
 * Ausgabeformat beibringen.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Bereitstellung von Input/Output-Paaren im Prompt, die das gewünschte Format demonstrieren
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen Few-Shot Prompt, der ein spezifisches Ausgabeformat demonstriert
 *    (z.B. für die Extraktion von Name und E-Mail aus einem Text)
 * 3. Sende den Prompt an das Modell
 * 4. Prüfe, ob die Ausgabe dem vorgegebenen Format entspricht
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class FewShotForFormatting {
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
        // Few-Shot Prompt für die Extraktion von Name und E-Mail
        String fewShotPrompt = "Extrahiere Name und Email.\n" +
                "Text: 'Kontakt: Max Mustermann (max.m@example.com)'\n" +
                "Output: Name: Max Mustermann, Email: max.m@example.com\n" +
                "Text: 'Melde dich bei Lisa Schmidt unter lisa.s@test.org.'\n" +
                "Output:";
        // TODO: Erstelle die Chat-Completion-Parameter für den Few-Shot Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(fewShotPrompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort auf den Few-Shot Prompt für Formatierung:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Prüfe, ob die Ausgabe dem vorgegebenen Format entspricht
        System.out.println("Prüfung des Ausgabeformats:");
        // Dein Kommentar hier...
        // Optional: Teste mit einem weiteren Beispiel
        System.out.println("\nOptional: Test mit einem weiteren Beispiel");
        String additionalTest = "Text: 'Für Rückfragen steht Ihnen Herr Dr. Thomas Müller (t.mueller@firma.de) zur Verfügung.'\nOutput:";
        // TODO: Implementiere den Test mit einem weiteren Beispiel
    }
}
