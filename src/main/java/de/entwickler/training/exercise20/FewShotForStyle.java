 
package de.entwickler.training.exercise20;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 20: Few-Shot für Stil (Mistral Large)
 * 
 * Ziel: Dem Mistral Large-Modell durch Beispiele einen spezifischen Schreibstil
 * oder eine bestimmte Art der Argumentation beibringen.
 * 
 * Modell: Mistral Large
 * 
 * Wichtige Parameter/Konzepte:
 * - Beispiele, die den gewünschten Stil demonstrieren
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Erstelle einen Few-Shot Prompt, der einen spezifischen Schreibstil demonstriert
 *    (z.B. für die Vereinfachung technischer Texte)
 * 3. Sende den Prompt an das Modell
 * 4. Beurteile, ob die generierte Antwort den gewünschten Stil trifft
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class FewShotForStyle {
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
        // Few-Shot Prompt für die Vereinfachung technischer Texte
        String fewShotPrompt = "Fasse den Text extrem einfach zusammen.\n" +
                "Text: 'Die Photosynthese ist ein Prozess, bei dem Lichtenergie in chemische Energie umgewandelt wird.'\n" +
                "Zusammenfassung: Pflanzen machen aus Sonnenlicht Essen.\n" +
                "Text: 'Agile Softwareentwicklung ist ein iterativer Ansatz zur Projektabwicklung.'\n" +
                "Zusammenfassung:";
        // TODO: Erstelle die Chat-Completion-Parameter für den Few-Shot Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(fewShotPrompt)
        //         .model("/models/Mistral-Large-Instruct-2411-FP8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort auf den Few-Shot Prompt für Stil:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Beurteile, ob die generierte Antwort den gewünschten Stil trifft
        System.out.println("Beurteilung des Stils:");
        // Dein Kommentar hier...
        // Optional: Teste mit einem weiteren Beispiel
        System.out.println("\nOptional: Test mit einem weiteren Beispiel");
        String additionalTest = "Text: 'Objektorientierte Programmierung basiert auf dem Konzept von Klassen und Objekten, die Daten und Verhalten kapseln.'\nZusammenfassung:";
        // TODO: Implementiere den Test mit einem weiteren Beispiel
    }
}
