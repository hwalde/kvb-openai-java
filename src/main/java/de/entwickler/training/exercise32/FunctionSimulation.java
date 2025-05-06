package de.entwickler.training.exercise32;

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
import java.util.List;

/**
 * Übung 32: Funktion simulieren & Ergebnis senden (gpt-4o-mini)
 *
 * Ziel: Den Funktionsaufruf "ausführen" (hier: simulieren) und das Ergebnis in einer
 * nachfolgenden API-Anfrage an das Modell zurückmelden.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Erstellen einer neuen ChatMessage mit der Rolle "tool"
 * - Angabe der tool_call_id aus der vorherigen Antwort
 * - Bereitstellung des (simulierten) Funktionsergebnisses als String-Inhalt dieser Nachricht
 * - Beibehaltung des bisherigen Chat-Verlaufs
 *
 * Aufgabe:
 * 1. Verwende die in Übung 29 definierte Funktion getWetter
 * 2. Sende einen Prompt, der das Modell dazu bringt, diese Funktion aufzurufen
 * 3. Extrahiere die tool_call_id und die Argumente aus der Antwort
 * 4. Simuliere die Ausführung der Funktion (hier: gib ein festes Wetterergebnis zurück)
 * 5. Sende eine neue Anfrage mit dem ursprünglichen Verlauf + der Tool-Antwort-Nachricht
 * 6. Gib die finale Antwort des Modells aus
 *
 * Hinweis: In dieser Übung simulieren wir die Ausführung der Funktion und senden
 * das Ergebnis zurück an das Modell, damit es eine menschenlesbare Antwort generieren kann.
 */
public class FunctionSimulation {

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
        String prompt = "Wie ist das aktuelle Wetter in Hamburg?";

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
        //     String functionName = toolCall.getFunction().getName();
        //     String arguments = toolCall.getFunction().getArguments();
        //     
        //     System.out.println("Funktionsname: " + functionName);
        //     System.out.println("Argumente: " + arguments);
        //     
        //     // Simuliere die Ausführung der Funktion
        //     String functionResult = "20°C, leicht bewölkt";
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
