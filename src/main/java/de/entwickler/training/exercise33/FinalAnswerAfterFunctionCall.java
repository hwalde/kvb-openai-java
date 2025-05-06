package de.entwickler.training.exercise33;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.function.FunctionDef;
import io.github.sashirestela.openai.common.function.FunctionExecutor;
import io.github.sashirestela.openai.common.function.Functional;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.ToolMessage;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.util.ArrayList;

/**
 * Übung 33: Finale Antwort nach Funktionsaufruf (gpt-4o-mini)
 *
 * Ziel: Die endgültige, menschenlesbare Antwort des Modells erhalten, nachdem es
 * das Ergebnis des Funktionsaufrufs verarbeitet hat.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Auslesen der finalen Antwort aus dem message-Objekt der API-Antwort auf die
 *   Anfrage mit dem Funktionsergebnis
 * - Analyse der Qualität der finalen Antwort
 *
 * Aufgabe:
 * 1. Verwende die in Übung 29 definierte Funktion getWetter
 * 2. Sende einen Prompt, der das Modell dazu bringt, diese Funktion aufzurufen
 * 3. Extrahiere die tool_call_id und die Argumente aus der Antwort
 * 4. Simuliere die Ausführung der Funktion mit verschiedenen Wetterergebnissen
 * 5. Sende eine neue Anfrage mit dem ursprünglichen Verlauf + der Tool-Antwort-Nachricht
 * 6. Gib die finale Antwort des Modells aus und analysiere, wie gut das Modell
 *    das Funktionsergebnis in eine natürlichsprachliche Antwort umgewandelt hat
 *
 * Hinweis: Diese Übung konzentriert sich auf die Qualität der finalen Antwort
 * und wie gut das Modell das Funktionsergebnis in eine menschenlesbare Form bringt.
 */
public class FinalAnswerAfterFunctionCall {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Definiere die Funktion getWetter mit dem Parameter stadt
        var functionExecutor = new FunctionExecutor();
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("getWetter")
                        .description("Ruft das aktuelle Wetter für eine angegebene Stadt ab")
                        .functionalClass(Wetter.class)
                        .strict(Boolean.TRUE)
                        .build());

        // Prompt, der das Modell dazu bringen sollte, die Funktion aufzurufen
        String prompt = "Wie ist das aktuelle Wetter in Berlin?";

        // Liste für den Chat-Verlauf
        var messages = new ArrayList<io.github.sashirestela.openai.domain.chat.ChatMessage>();
        messages.add(UserMessage.of(prompt));

        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt und der Funktionsdefinition
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .messages(messages)
        //         .tools(functionExecutor.getToolFunctions())
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere die tool_call_id und die Argumente aus der Antwort
        // Beispiel:
        // var chatMessage = chatResponse.firstMessage();
        // messages.add(chatMessage); // Füge die Antwort des Modells zum Chat-Verlauf hinzu
        // 
        // if (chatMessage.getToolCalls() != null && !chatMessage.getToolCalls().isEmpty()) {
        //     var toolCall = chatMessage.getToolCalls().get(0);
        //     String toolCallId = toolCall.getId();
        //     
        //     // Simuliere die Ausführung der Funktion mit einem detaillierteren Ergebnis
        //     String functionResult = "{ \"temperatur\": \"22°C\", \"zustand\": \"sonnig\", \"luftfeuchtigkeit\": \"45%\", \"windgeschwindigkeit\": \"10 km/h\" }";
        //     
        //     // Füge die Tool-Antwort zum Chat-Verlauf hinzu
        //     messages.add(ToolMessage.of(functionResult, toolCallId));
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
        //     
        //     // Analysiere die Qualität der Antwort
        //     System.out.println("\nAnalyse der Antwort:");
        //     System.out.println("- Hat das Modell alle Wetterdaten aus dem Funktionsergebnis verwendet?");
        //     System.out.println("- Ist die Antwort natürlichsprachlich und gut lesbar?");
        //     System.out.println("- Wurden zusätzliche, nicht im Funktionsergebnis enthaltene Informationen hinzugefügt?");
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

        System.out.println("Analyse der Antwort:");
        // Dein Kommentar hier...
    }

    public static class Wetter implements Functional {

        @JsonPropertyDescription("Die Stadt, für die das Wetter abgefragt werden soll")
        @JsonProperty(required = true)
        public String stadt;

        @Override
        public Object execute() {
            // In einer echten Anwendung würde hier eine API-Anfrage an einen Wetterdienst erfolgen
            return "{ \"temperatur\": \"22°C\", \"zustand\": \"sonnig\", \"luftfeuchtigkeit\": \"45%\", \"windgeschwindigkeit\": \"10 km/h\" }";
        }
    }
}
