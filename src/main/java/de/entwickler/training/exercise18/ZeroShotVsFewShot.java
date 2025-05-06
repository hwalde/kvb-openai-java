 
package de.entwickler.training.exercise18;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 18: Zero-Shot vs. Few-Shot (Mistral Small)
 * 
 * Ziel: Den Nutzen von Beispielen (Few-Shot Learning) demonstrieren, um die Leistung
 * bei einer spezifischen Aufgabe zu verbessern, insbesondere bei kleineren Modellen.
 * 
 * Modell: Mistral Small
 * 
 * Wichtige Parameter/Konzepte:
 * - Zero-Shot (nur Anweisung)
 * - Few-Shot (Anweisung + 1-2 Beispiele von Input/Output Paaren im Prompt)
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Small Modell (wie in Übung 12)
 * 2. Erstelle zwei verschiedene Prompts für die Stimmungsklassifizierung:
 *    a) Zero-Shot Prompt: "Klassifiziere die Stimmung: 'Das Wetter ist schön.'"
 *    b) Few-Shot Prompt: "Klassifiziere die Stimmung.\nSatz: 'Ich liebe diesen Film.'\nStimmung: POSITIV\n
 *       Satz: 'Das Essen war schlecht.'\nStimmung: NEGATIV\nSatz: 'Das Wetter ist schön.'\nStimmung:"
 * 3. Sende beide Prompts an das Modell
 * 4. Vergleiche die Zuverlässigkeit der Klassifizierung
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class ZeroShotVsFewShot {
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
        // Zero-Shot Prompt
        String zeroShotPrompt = "Klassifiziere die Stimmung: 'Das Wetter ist schön.'";
        // Few-Shot Prompt
        String fewShotPrompt = "Klassifiziere die Stimmung.\n" +
                "Satz: 'Ich liebe diesen Film.'\n" +
                "Stimmung: POSITIV\n" +
                "Satz: 'Das Essen war schlecht.'\n" +
                "Stimmung: NEGATIV\n" +
                "Satz: 'Das Wetter ist schön.'\n" +
                "Stimmung:";
        // TODO: Erstelle die Chat-Completion-Parameter für den Zero-Shot Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(zeroShotPrompt)
        //         .model("/models/Mistral-Small-3.1-24B-Instruct-2503/")
        //         .build();
        // TODO: Sende die Anfrage mit dem Zero-Shot Prompt an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf den Zero-Shot Prompt aus
        System.out.println("Antwort auf den Zero-Shot Prompt:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Erstelle die Chat-Completion-Parameter für den Few-Shot Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(fewShotPrompt)
        //         .model("/models/Mistral-Small-3.1-24B-Instruct-2503/")
        //         .build();
        // TODO: Sende die Anfrage mit dem Few-Shot Prompt an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf den Few-Shot Prompt aus
        System.out.println("Antwort auf den Few-Shot Prompt:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Vergleiche die Zuverlässigkeit der Klassifizierung
        System.out.println("Vergleich der Zuverlässigkeit der Klassifizierung:");
        // Dein Kommentar hier...
    }
}
