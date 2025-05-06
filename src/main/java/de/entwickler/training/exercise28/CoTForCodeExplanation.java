 
package de.entwickler.training.exercise28;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 28: CoT für Code-Erklärung (Qwen)
 * 
 * Ziel: Chain-of-Thought nutzen, um eine detaillierte, schrittweise Erklärung
 * eines Code-Snippets von Qwen zu erhalten.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Kombination eines Code-Snippets mit der Anweisung "Erkläre diesen Code Schritt für Schritt"
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen Prompt, der ein Code-Snippet enthält und das Modell anweist,
 *    den Code Schritt für Schritt zu erklären
 * 3. Sende den Prompt an das Modell
 * 4. Analysiere, ob die Erklärung logisch aufgebaut ist und die einzelnen Teile des Codes abdeckt
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class CoTForCodeExplanation {
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
        // Prompt mit Code-Snippet und Anweisung zur schrittweisen Erklärung
        String codeExplanationPrompt = "Erkläre den folgenden Java-Code Schritt für Schritt:\n\n" +
                "```java\n" +
                "public int factorial(int n) {\n" +
                "    if (n == 0) {\n" +
                "        return 1;\n" +
                "    } else {\n" +
                "        return n * factorial(n-1);\n" +
                "    }\n" +
                "}\n" +
                "```";
        // TODO: Erstelle die Chat-Completion-Parameter für den Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(codeExplanationPrompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Schrittweise Erklärung des Code-Snippets:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Analysiere, ob die Erklärung logisch aufgebaut ist und die einzelnen Teile des Codes abdeckt
        System.out.println("Analyse der Erklärung:");
        // Dein Kommentar hier...
        // Hinweis: Prüfe, ob die Erklärung folgende Aspekte abdeckt:
        // - Die Funktion der Methode (Berechnung der Fakultät)
        // - Die Bedeutung des Parameters n
        // - Die Basisfall-Bedingung (n == 0)
        // - Den rekursiven Aufruf
        // - Die Rückgabewerte
    }
}
