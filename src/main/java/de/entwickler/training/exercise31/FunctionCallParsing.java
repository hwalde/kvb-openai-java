package de.entwickler.training.exercise31;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.function.FunctionDef;
import io.github.sashirestela.openai.common.function.FunctionExecutor;
import io.github.sashirestela.openai.common.function.Functional;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

/**
 * Übung 31: Funktionsaufruf parsen (gpt-4o-mini)
 *
 * Ziel: Aus der API-Antwort erkennen, ob das Modell eine Funktion aufrufen möchte,
 * und wenn ja, welche Funktion mit welchen Argumenten.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Überprüfung des finish_reason (sollte "tool_calls" sein)
 * - Zugriff auf das tool_calls-Array in der Antwort
 * - Extrahieren von Funktionsname und Argumenten (als JSON-String)
 *
 * Aufgabe:
 * 1. Verwende die in Übung 29 definierte Funktion getWetter
 * 2. Sende einen Prompt, der das Modell dazu bringen sollte, diese Funktion aufzurufen
 *    (z.B. "Wie ist das aktuelle Wetter in Hamburg?")
 * 3. Analysiere die Antwort und extrahiere:
 *    - Den Namen der aufgerufenen Funktion ("getWetter")
 *    - Die Argumente (JSON-String wie {"stadt": "Hamburg"})
 * 4. Gib diese Informationen aus
 *
 * Hinweis: Diese Übung konzentriert sich auf das Parsen und Extrahieren der
 * Funktionsaufruf-Informationen aus der API-Antwort.
 */
public class FunctionCallParsing {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Definiere die Funktion getWetter mit dem Parameter stadt (wie in Übung 29)
        var functionExecutor = new FunctionExecutor();
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("getWetter")
                        .description("Ruft das aktuelle Wetter für eine angegebene Stadt ab")
                        .functionalClass(Wetter.class)
                        .strict(Boolean.TRUE)
                        .build());

        // Prompt, der das Modell dazu bringen sollte, die Funktion aufzurufen
        String prompt = "Wie ist das aktuelle Wetter in Hamburg?";

        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt und der Funktionsdefinition
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(prompt))
        //         .tools(functionExecutor.getToolFunctions())
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Analysiere die Antwort und extrahiere die Funktionsaufruf-Informationen
        // Hinweis: Überprüfe zunächst, ob das Modell überhaupt eine Funktion aufrufen möchte
        // Beispiel:
        // var chatMessage = chatResponse.firstMessage();
        // if (chatMessage.getToolCalls() != null && !chatMessage.getToolCalls().isEmpty()) {
        //     var toolCall = chatMessage.getToolCalls().get(0);
        //     String functionName = toolCall.getFunction().getName();
        //     String arguments = toolCall.getFunction().getArguments();
        //     
        //     System.out.println("Funktionsname: " + functionName);
        //     System.out.println("Argumente: " + arguments);
        // } else {
        //     System.out.println("Das Modell möchte keine Funktion aufrufen.");
        // }

        System.out.println("Antwort von gpt-4o-mini:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        System.out.println("Extrahierte Funktionsaufruf-Informationen:");
        // Dein Code hier...
        
        // Optional: Verwende eine JSON-Bibliothek, um die Argumente zu parsen und strukturiert auszugeben
        System.out.println("\nOptional: Strukturierte Ausgabe der Argumente");
        // Dein Code hier...
    }

    public static class Wetter implements Functional {

        @JsonPropertyDescription("Die Stadt, für die das Wetter abgefragt werden soll")
        @JsonProperty(required = true)
        public String stadt;

        @Override
        public Object execute() {
            // In einer echten Anwendung würde hier eine API-Anfrage an einen Wetterdienst erfolgen
            return "20°C, leicht bewölkt";
        }
    }
}
