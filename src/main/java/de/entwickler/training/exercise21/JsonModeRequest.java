 
package de.entwickler.training.exercise21;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 21: JSON Mode anfordern (gpt-4o-mini)
 * 
 * Ziel: Die Fähigkeit von gpt-4o-mini nutzen, garantiert valides JSON auszugeben,
 * indem der spezielle JSON Mode der API aktiviert wird.
 * 
 * Modell: gpt-4o-mini
 * 
 * Wichtige Parameter/Konzepte:
 * - Setzen des response_format-Parameters auf { "type": "json_object" } im ChatResponseRequest
 * - Prompt muss das Wort "JSON" enthalten
 * 
 * Aufgabe:
 * 1. Erstelle einen Prompt, der das Modell anweist, ein JSON-Objekt zu generieren
 *    (z.B. "Generiere ein JSON-Objekt mit den Schlüsseln 'name' und 'stadt' für eine Person namens Max in Berlin.")
 * 2. Setze den response_format-Parameter auf { "type": "json_object" }
 * 3. Sende die Anfrage an die OpenAI API
 * 4. Gib die Antwort aus und prüfe, ob sie valides JSON ist
 * 
 * Hinweis: Der JSON Mode ist nur bei bestimmten Modellen verfügbar, darunter gpt-4o-mini.
 * Ältere Modelle wie gpt-3.5-turbo unterstützen diesen Modus nicht.
 */
public class JsonModeRequest {
    public static void main(String[] args) {
        // Prompt, der das Modell anweist, ein JSON-Objekt zu generieren
        String prompt = "Generiere ein JSON-Objekt mit den Schlüsseln 'name' und 'stadt' für eine Person namens Max in Berlin.";
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und dem JSON Mode
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(prompt)
        //         .model(ChatModel.GPT_4_1_MINI)
        //         .responseFormat(ChatRequest.ResponseFormat.builder().type("json_object").build())
        //         .build();
        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort im JSON Mode:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Prüfe, ob die Antwort valides JSON ist
        System.out.println("Prüfung des JSON-Formats:");
        // Dein Code hier...
        // Hinweis: Du kannst versuchen, die Antwort mit einer JSON-Bibliothek wie Jackson zu parsen
        // Optional: Vergleiche mit einer Anfrage ohne JSON Mode
        System.out.println("\nOptional: Vergleich mit einer Anfrage ohne JSON Mode");
        // TODO: Implementiere den Vergleich mit einer Anfrage ohne JSON Mode
    }
}
