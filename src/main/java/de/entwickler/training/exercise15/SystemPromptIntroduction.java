
package de.entwickler.training.exercise15;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 15: System Prompt Einführung (Qwen)
 * 
 * Ziel: Die System-Rolle nutzen, um dem Qwen-Modell eine übergeordnete Verhaltensanweisung 
 * oder Persona zu geben.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Verwendung von ChatMessage mit der Rolle "system" zusätzlich zur "user"-Nachricht
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Erstelle einen System-Prompt, der dem Modell eine übergeordnete Rolle zuweist
 *    (z.B. "Du bist ein hilfreicher Assistent für Java-Entwickler.")
 * 3. Erstelle einen User-Prompt mit einer Frage
 *    (z.B. "Was ist der Unterschied zwischen ArrayList und LinkedList?")
 * 4. Sende die Anfrage an das Modell
 * 5. Gib die Antwort aus und beobachte, ob der Ton oder Inhalt der Antwort die System-Anweisung widerspiegelt
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class SystemPromptIntroduction {
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
        // System-Prompt
        String systemPrompt = "Du bist ein hilfreicher Assistent für Java-Entwickler.";
        // User-Prompt
        String userPrompt = "Was ist der Unterschied zwischen ArrayList und LinkedList?";
        // TODO: Erstelle die Chat-Completion-Parameter mit dem System-Prompt, User-Prompt und der Model-ID
        // Hinweis: Verwende ChatRequest.builder()
        //         .addSystemMessage(systemPrompt)
        //         .addUserMessage(userPrompt)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell mit System-Prompt:");
        // Dein Code hier...
        // TODO: Beobachte, ob der Ton oder Inhalt der Antwort die System-Anweisung widerspiegelt
        System.out.println("\nBeobachtung zur Wirkung des System-Prompts:");
        // Dein Kommentar hier...
        // Optional: Vergleiche mit einer Anfrage ohne System-Prompt
        System.out.println("\nOptional: Vergleich mit einer Anfrage ohne System-Prompt");
        // TODO: Implementiere den Vergleich mit einer Anfrage ohne System-Prompt
    }
}
