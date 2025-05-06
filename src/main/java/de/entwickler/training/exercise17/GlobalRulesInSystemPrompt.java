 
package de.entwickler.training.exercise17;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 17: Globale Regeln im System Prompt (Qwen)
 * 
 * Ziel: Eine feste Verhaltensregel im System-Prompt definieren und deren Einhaltung
 * über mehrere Interaktionen (theoretisch) prüfen.
 * 
 * Modell: Qwen-3 32B
 * 
 * Wichtige Parameter/Konzepte:
 * - Formulierung einer klaren, globalen Regel im System-Prompt
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell (wie in Übung 10)
 * 2. Definiere einen System-Prompt mit einer klaren globalen Regel
 *    (z.B. "Antworte ausschließlich in Stichpunkten.")
 * 3. Sende zwei verschiedene User-Prompts an das Modell:
 *    a) "Was sind die SOLID-Prinzipien?"
 *    b) "Erkläre Dependency Injection."
 * 4. Prüfe, ob beide Antworten die im System-Prompt definierte Regel einhalten
 * 
 * Hinweis: In einer echten Konversation würde der System-Prompt für alle Nachrichten gelten.
 * Da wir hier separate Anfragen senden, müssen wir den System-Prompt für jede Anfrage wiederholen.
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class GlobalRulesInSystemPrompt {
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
        // System-Prompt mit globaler Regel
        String systemPrompt = "Antworte ausschließlich in Stichpunkten.";
        // Erster User-Prompt
        String userPrompt1 = "Was sind die SOLID-Prinzipien?";
        // TODO: Erstelle die Chat-Completion-Parameter für die erste Anfrage
        // Hinweis: Verwende ChatRequest.builder()
        //         .addSystemMessage(systemPrompt)
        //         .addUserMessage(userPrompt1)
        //         .model("/models/qwen3-32b-fp8")
        //         .build();
        // TODO: Sende die erste Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf die erste Anfrage aus
        System.out.println("Antwort auf die erste Anfrage (SOLID-Prinzipien):");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // Zweiter User-Prompt
        String userPrompt2 = "Erkläre Dependency Injection.";
        // TODO: Erstelle die Chat-Completion-Parameter für die zweite Anfrage
        // Hinweis: Verwende denselben System-Prompt wie bei der ersten Anfrage
        // TODO: Sende die zweite Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort auf die zweite Anfrage aus
        System.out.println("Antwort auf die zweite Anfrage (Dependency Injection):");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Prüfe, ob beide Antworten die im System-Prompt definierte Regel einhalten
        System.out.println("Prüfung der Einhaltung der globalen Regel:");
        // Dein Kommentar hier...
    }
}
