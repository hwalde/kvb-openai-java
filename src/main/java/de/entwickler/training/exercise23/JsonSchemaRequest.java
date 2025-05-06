 
package de.entwickler.training.exercise23;

import de.entwickler.training.util.OpenAIClientService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Übung 23: JSON Schema vorgeben (gpt-4o-mini)
 * 
 * Ziel: Dem gpt-4o-mini im JSON Mode eine gewünschte Struktur (implizit durch Beschreibung) vorgeben.
 * 
 * Modell: gpt-4o-mini
 * 
 * Wichtige Parameter/Konzepte:
 * - response_format={ "type": "json_object" }
 * - Beschreibung der gewünschten JSON-Struktur im Prompt
 * 
 * Aufgabe:
 * 1. Erstelle einen Prompt, der eine komplexere JSON-Struktur beschreibt
 *    (z.B. "Erstelle ein JSON-Objekt, das eine Person repräsentiert. Es soll einen Schlüssel 'name' (String),
 *    einen Schlüssel 'alter' (Integer) und einen Schlüssel 'hobbies' (Array von Strings) enthalten.
 *    Beispielwerte: Name 'Erik', Alter 25, Hobbies ['Lesen', 'Radfahren']. Gib nur das JSON aus.")
 * 2. Setze den response_format-Parameter auf { "type": "json_object" }
 * 3. Sende die Anfrage an die OpenAI API
 * 4. Gib die Antwort aus und prüfe, ob sie der gewünschten Struktur entspricht
 * 
 * Hinweis: Der JSON Mode ist nur bei bestimmten Modellen verfügbar, darunter gpt-4o-mini.
 */
public class JsonSchemaRequest {
    public static void main(String[] args) {
        // Prompt, der eine komplexere JSON-Struktur beschreibt
        String prompt = "Erstelle ein JSON-Objekt, das eine Person repräsentiert. Es soll einen Schlüssel 'name' (String), " +
                "einen Schlüssel 'alter' (Integer) und einen Schlüssel 'hobbies' (Array von Strings) enthalten. " +
                "Beispielwerte: Name 'Erik', Alter 25, Hobbies ['Lesen', 'Radfahren']. Gib nur das JSON aus.";
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
        System.out.println("Antwort im JSON Mode mit Schema-Vorgabe:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Prüfe, ob die Antwort der gewünschten Struktur entspricht
        System.out.println("Prüfung der JSON-Struktur:");
        // Dein Code hier...
        // Hier ist eine Hilfsmethode zum Prüfen der JSON-Struktur
        // Du kannst sie verwenden, um die Antwort zu überprüfen
        // String jsonString = "..."; // Die Antwort vom Modell
        // checkJsonStructure(jsonString);
    }
    /**
     * Hilfsmethode zum Überprüfen der JSON-Struktur
     * 
     * @param jsonString Der zu überprüfende JSON-String
     */
    private static void checkJsonStructure(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            // Prüfe, ob die erwarteten Felder vorhanden sind
            boolean hasName = jsonNode.has("name") && jsonNode.get("name").isTextual();
            boolean hasAge = jsonNode.has("alter") && jsonNode.get("alter").isInt();
            boolean hasHobbies = jsonNode.has("hobbies") && jsonNode.get("hobbies").isArray();
            System.out.println("Hat 'name' (String): " + hasName);
            System.out.println("Hat 'alter' (Integer): " + hasAge);
            System.out.println("Hat 'hobbies' (Array): " + hasHobbies);
            if (hasHobbies) {
                System.out.println("Hobbies: ");
                JsonNode hobbies = jsonNode.get("hobbies");
                for (int i = 0; i < hobbies.size(); i++) {
                    System.out.println("  - " + hobbies.get(i).asText());
                }
            }
            System.out.println("Struktur entspricht den Anforderungen: " + (hasName && hasAge && hasHobbies));
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen des JSON: " + e.getMessage());
        }
    }
}
