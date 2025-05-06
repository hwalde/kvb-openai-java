package de.entwickler.training.exercise29;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.function.FunctionDef;
import io.github.sashirestela.openai.common.function.FunctionExecutor;
import io.github.sashirestela.openai.common.function.Functional;
import io.github.sashirestela.openai.domain.chat.ChatRequest;


import java.util.List;

/**
 * Übung 29: Funktion definieren (gpt-4o-mini)
 *
 * Ziel: Lernen, wie man eine Funktion für die OpenAI API im korrekten JSON-Schema-Format
 * definiert, damit gpt-4o-mini sie potenziell aufrufen kann.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Struktur des tools-Parameters im ChatRequest
 * - Definition von Funktionsnamen, Beschreibung, Parametern (Typ, Beschreibung, erforderliche Parameter)
 *
 * Aufgabe:
 * 1. Definiere eine Funktion getWetter mit einem Parameter stadt (Typ String,
 *    Beschreibung "Die Stadt, für die das Wetter abgefragt werden soll", erforderlich)
 * 2. Erstelle einen Prompt, der das Modell dazu bringen könnte, diese Funktion zu nutzen
 *    (z.B. "Wie ist das aktuelle Wetter in Hamburg?")
 * 3. Sende die Anfrage an die OpenAI API mit der Funktionsdefinition
 * 4. Prüfe, ob das Modell die Funktion aufrufen möchte
 *
 * Hinweis: In dieser Übung geht es nur darum, die Funktion zu definieren und zu sehen,
 * ob das Modell sie aufrufen möchte. Die tatsächliche Ausführung der Funktion erfolgt in Übung 32.
 */
public class FunctionDefinition {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere die Funktion getWetter mit dem Parameter stadt
        // Hinweis: Verwende FunctionDef.builder()...

        // Hier ist ein Beispiel für die Definition der Funktion getWetter:
        var functionExecutor = new FunctionExecutor();
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("getWetter")
                        .description("Ruft das aktuelle Wetter für eine angegebene Stadt ab")
                        .functionalClass(Wetter.class)
                        .strict(Boolean.TRUE)
                        .build());

        // Prompt, der das Modell dazu bringen könnte, die Funktion zu nutzen
        String prompt = "Wie ist das aktuelle Wetter in Hamburg?";

        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt und der Funktionsdefinition
        // Hinweis: Verwende ChatRequest.builder()
        // Beispiel:
        // var chatRequest = ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(prompt))
        //         .tools(functionExecutor.getToolFunctions())
        //         .build();


        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Beispiel:
        // var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Prüfe, ob das Modell die Funktion aufrufen möchte
        // Hinweis: Überprüfe die toolCalls in der Antwort
        // Beispiel:
        // var chatMessage = chatResponse.firstMessage();
        // if (chatMessage.getToolCalls() != null && !chatMessage.getToolCalls().isEmpty()) {
        //     var toolCall = chatMessage.getToolCalls().get(0);
        //     System.out.println("Das Modell möchte die Funktion '" + toolCall.getFunction().getName() + "' aufrufen.");
        //     System.out.println("Argumente: " + toolCall.getFunction().getArguments());
        // } else {
        //     System.out.println("Das Modell möchte keine Funktion aufrufen.");
        // }

        System.out.println("Antwort von gpt-4o-mini:");
        // Dein Code hier...
        // System.out.println(chatResponse.firstContent());

        System.out.println("\n----------------------------------------\n");

        System.out.println("Prüfung, ob das Modell die Funktion aufrufen möchte:");
        // Dein Code hier...
        // Beispiel:
        // var chatMessage = chatResponse.firstMessage();
        // if (chatMessage.getToolCalls() != null && !chatMessage.getToolCalls().isEmpty()) {
        //     var toolCall = chatMessage.getToolCalls().get(0);
        //     System.out.println("Das Modell möchte die Funktion '" + toolCall.getFunction().getName() + "' aufrufen.");
        //     System.out.println("Argumente: " + toolCall.getFunction().getArguments());
        // } else {
        //     System.out.println("Das Modell möchte keine Funktion aufrufen.");
        // }
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
