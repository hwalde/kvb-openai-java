package de.entwickler.training.exercise30;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.function.FunctionDef;
import io.github.sashirestela.openai.common.function.FunctionExecutor;
import io.github.sashirestela.openai.common.function.Functional;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

/**
 * Übung 30: Funktionstrigger provozieren (gpt-4o-mini)
 *
 * Ziel: Einen Prompt formulieren, der gpt-4o-mini dazu bringt, die zuvor definierte Funktion nutzen zu wollen.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - User-Prompt, der klar auf die Funktionalität der definierten Funktion abzielt
 * - Übergabe der Funktionsdefinition im tools-Parameter
 *
 * Aufgabe:
 * 1. Verwende die in Übung 29 definierte Funktion getWetter
 * 2. Formuliere einen Prompt, der das Modell dazu bringen sollte, diese Funktion aufrufen zu wollen
 *    (z.B. "Wie ist das aktuelle Wetter in Hamburg?")
 * 3. Sende die Anfrage an die OpenAI API mit der Funktionsdefinition
 * 4. Prüfe, ob das Modell die Funktion aufrufen möchte
 *
 * Hinweis: In dieser Übung geht es darum, einen geeigneten Prompt zu formulieren,
 * der das Modell dazu bringt, die definierte Funktion nutzen zu wollen.
 */
public class FunctionTrigger {

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

        // TODO: Formuliere einen Prompt, der das Modell dazu bringen sollte, die Funktion aufzurufen
        // Beispiel: "Wie ist das aktuelle Wetter in Hamburg?"
        String prompt = ""; // Dein Prompt hier

        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt und der Funktionsdefinition
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(prompt))
        //         .tools(functionExecutor.getToolFunctions())
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

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
