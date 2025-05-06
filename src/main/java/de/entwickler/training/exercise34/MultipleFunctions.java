package de.entwickler.training.exercise34;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.function.FunctionDef;
import io.github.sashirestela.openai.common.function.FunctionExecutor;
import io.github.sashirestela.openai.common.function.Functional;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Übung 34: Mehrere Funktionen definieren (gpt-4o-mini)
 *
 * Ziel: Dem Modell mehrere Werkzeuge zur Verfügung stellen und beobachten,
 * ob es das passende auswählt.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Definition von zwei oder mehr Funktionen im tools-Array
 * - Beobachtung der Funktionsauswahl durch das Modell
 *
 * Aufgabe:
 * 1. Definiere zwei Funktionen:
 *    a) getWetter (wie in den vorherigen Übungen)
 *    b) getAktuelleZeit (ohne Parameter)
 * 2. Stelle eine Frage, die beide Funktionen betrifft
 *    (z.B. "Wie spät ist es jetzt in Berlin und wie ist dort das Wetter?")
 * 3. Beobachte, welche Funktion(en) das Modell aufruft
 * 4. Simuliere die Ausführung der aufgerufenen Funktion(en) und sende das Ergebnis zurück
 * 5. Gib die finale Antwort des Modells aus
 *
 * Hinweis: Diese Übung zeigt, wie das Modell zwischen verschiedenen verfügbaren
 * Funktionen auswählt und ob es in der Lage ist, mehrere Funktionen nacheinander aufzurufen.
 */
public class MultipleFunctions {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere zwei Funktionen: getWetter und getAktuelleZeit
        var functionExecutor = new FunctionExecutor();
        // Beispiel:
        // functionExecutor.enrollFunction(
        //         FunctionDef.builder()
        //                 .name("getWetter")
        //                 .description("Ruft das aktuelle Wetter für eine angegebene Stadt ab")
        //                 .functionalClass(Wetter.class)
        //                 .strict(Boolean.TRUE)
        //                 .build());
        // 
        // functionExecutor.enrollFunction(
        //         FunctionDef.builder()
        //                 .name("getAktuelleZeit")
        //                 .description("Gibt die aktuelle Uhrzeit zurück")
        //                 .functionalClass(AktuelleZeit.class)
        //                 .strict(Boolean.TRUE)
        //                 .build());

        // Prompt, der beide Funktionen betreffen könnte
        String prompt = "Wie spät ist es jetzt in Berlin und wie ist dort das Wetter?";

        // Liste für den Chat-Verlauf
        var messages = new ArrayList<io.github.sashirestela.openai.domain.chat.ChatMessage>();
        messages.add(UserMessage.of(prompt));

        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt und den Funktionsdefinitionen
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .messages(messages)
        //         .tools(functionExecutor.getToolFunctions())
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Analysiere, welche Funktion(en) das Modell aufrufen möchte
        // Beispiel:
        // var chatMessage = chatResponse.firstMessage();
        // messages.add(chatMessage); // Füge die Antwort des Modells zum Chat-Verlauf hinzu
        // 
        // if (chatMessage.getToolCalls() != null && !chatMessage.getToolCalls().isEmpty()) {
        //     System.out.println("Das Modell möchte " + chatMessage.getToolCalls().size() + " Funktion(en) aufrufen:");
        //     
        //     for (var toolCall : chatMessage.getToolCalls()) {
        //         String toolCallId = toolCall.getId();
        //         String functionName = toolCall.getFunction().getName();
        //         String arguments = toolCall.getFunction().getArguments();
        //         
        //         System.out.println("- Funktion: " + functionName);
        //         System.out.println("  Argumente: " + arguments);
        //         
        //         // Simuliere die Ausführung der Funktion
        //         String functionResult = "";
        //         if (functionName.equals("getWetter")) {
        //             functionResult = "{ \"temperatur\": \"18°C\", \"zustand\": \"bewölkt\" }";
        //         } else if (functionName.equals("getAktuelleZeit")) {
        //             functionResult = "{ \"zeit\": \"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\" }";
        //         }
        //         
        //         // Füge die Tool-Antwort zum Chat-Verlauf hinzu
        //         messages.add(ChatMessage.ToolMessage.of(functionResult, toolCallId));
        //     }
        //     
        //     // Erstelle eine neue Anfrage mit dem aktualisierten Chat-Verlauf
        //     var secondRequest = ChatRequest.builder()
        //             .model("gpt-4o-mini")
        //             .messages(messages)
        //             .build();
        //     
        //     // Sende die zweite Anfrage an die OpenAI API
        //     var secondResponse = openAIClient.chatCompletions().create(secondRequest).join();
        //     
        //     // Gib die finale Antwort aus
        //     System.out.println("\nFinale Antwort von gpt-4o-mini:");
        //     System.out.println(secondResponse.firstContent());
        // } else {
        //     System.out.println("Das Modell möchte keine Funktion aufrufen.");
        // }

        System.out.println("Erste Antwort von gpt-4o-mini (Funktionsaufruf):");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Simulierte Funktionsausführung:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Finale Antwort nach Funktionsausführung:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Analyse der Funktionsauswahl:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Hat das Modell die passende(n) Funktion(en) für die Anfrage ausgewählt?
        // - Hat es versucht, beide Funktionen aufzurufen oder nur eine?
        // - Wenn nur eine, welche hat es priorisiert und warum?
    }

    public static class Wetter implements Functional {

        @JsonPropertyDescription("Die Stadt, für die das Wetter abgefragt werden soll")
        @JsonProperty(required = true)
        public String stadt;

        @Override
        public Object execute() {
            // In einer echten Anwendung würde hier eine API-Anfrage an einen Wetterdienst erfolgen
            return "{ \"temperatur\": \"18°C\", \"zustand\": \"bewölkt\" }";
        }
    }

    public static class AktuelleZeit implements Functional {

        @Override
        public Object execute() {
            // Gibt die aktuelle Uhrzeit zurück
            return "{ \"zeit\": \"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\" }";
        }
    }
}
