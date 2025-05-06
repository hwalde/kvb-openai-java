package de.entwickler.training.exercise24;

import de.entwickler.training.util.OpenAIClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Übung 24: JSON Antwort parsen
 * 
 * Ziel: Die vom LLM generierte JSON-Antwort in Java verarbeiten.
 * 
 * Modell: gpt-4o-mini oder Qwen-3 32B (basierend auf Übung 21/22/23/25)
 * 
 * Wichtige Parameter/Konzepte:
 * - Verwendung einer Java JSON-Bibliothek (z.B. Jackson, Gson) zum Deserialisieren des JSON-Strings
 *   in ein passendes Java-Objekt (POJO)
 * - Fehlerbehandlung für ungültiges JSON
 * 
 * Aufgabe:
 * 1. Erstelle eine Java-Klasse (Person), die der erwarteten JSON-Struktur entspricht
 * 2. Erstelle einen Prompt, der das Modell anweist, ein JSON-Objekt zu generieren,
 *    das der Struktur der Person-Klasse entspricht
 * 3. Sende die Anfrage an die OpenAI API
 * 4. Deserialisiere die JSON-Antwort in eine Instanz der Person-Klasse
 * 5. Gib die Eigenschaften des Person-Objekts aus
 * 
 * Hinweis: Verwende den JSON Mode bei gpt-4o-mini oder eine explizite Anweisung bei Qwen-3 32B.
 */
public class JsonResponseParser {
    public static void main(String[] args) {
        // Prompt, der das Modell anweist, ein JSON-Objekt zu generieren
        String prompt = "Generiere ein JSON-Objekt für eine Person mit den Eigenschaften 'name' (String), " +
                "'alter' (Integer) und 'stadt' (String). Beispielwerte: Name 'Maria', Alter 30, Stadt 'München'. " +
                "Gib nur das JSON aus.";
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und dem JSON Mode
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(prompt)
        //         .model(ChatModel.GPT_4_1_MINI)
        //         .responseFormat(ChatRequest.ResponseFormat.builder().type("json_object").build())
        //         .build();
        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // TODO: Extrahiere die JSON-Antwort
        String jsonResponse = ""; // Ersetze dies durch die tatsächliche Antwort
        System.out.println("JSON-Antwort vom Modell:");
        System.out.println(jsonResponse);
        System.out.println("\n----------------------------------------\n");
        // TODO: Deserialisiere die JSON-Antwort in ein Person-Objekt
        try {
            ObjectMapper mapper = new ObjectMapper();
            Person person = mapper.readValue(jsonResponse, Person.class);
            // TODO: Gib die Eigenschaften des Person-Objekts aus
            System.out.println("Deserialisiertes Person-Objekt:");
            System.out.println("Name: " + person.getName());
            System.out.println("Alter: " + person.getAlter());
            System.out.println("Stadt: " + person.getStadt());
        } catch (Exception e) {
            System.out.println("Fehler beim Deserialisieren des JSON: " + e.getMessage());
        }
    }
    /**
     * Eine Java-Klasse, die der erwarteten JSON-Struktur entspricht
     */
    public static class Person {
        private String name;
        private int alter;
        private String stadt;
        // Standardkonstruktor (wird für die Deserialisierung benötigt)
        public Person() {
        }
        // Getter und Setter
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAlter() {
            return alter;
        }
        public void setAlter(int alter) {
            this.alter = alter;
        }
        public String getStadt() {
            return stadt;
        }
        public void setStadt(String stadt) {
            this.stadt = stadt;
        }
    }
}
