package de.entwickler.training.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.entwickler.training.util.OpenAIClientService; // Wiederverwendung
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.common.function.FunctionDef;
import io.github.sashirestela.openai.common.function.FunctionExecutor;
import io.github.sashirestela.openai.common.tool.ToolCall;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutonomousLLMAgent {

    private SimpleOpenAI openAIClient;
    private FunctionExecutor functionExecutor;
    private List<ChatMessage> chatHistory;
    private static TaskManager taskManager; // Statisch für Zugriff aus Funktionsklassen
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String MODEL_NAME = "gpt-4o-mini"; // oder "gpt-4-turbo" etc.

    public AutonomousLLMAgent() {
        this.openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        taskManager = new TaskManager(); // Einmal initialisieren
        this.functionExecutor = new FunctionExecutor();
        setupFunctions();
        this.chatHistory = new ArrayList<>();
    }

    // Statische Methode, damit die Funktionsklassen darauf zugreifen können
    public static TaskManager getTaskManagerForFunctions() {
        return taskManager;
    }

    private void setupFunctions() {
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("getOpenTasks")
                        .description("Ruft die aktuelle Liste der offenen Aufgaben ab, um zu entscheiden, was als Nächstes zu tun ist.")
                        .functionalClass(LLMAgentFunctions.GetOpenTasksFunction.class)
                        .build());
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("performWebSearch")
                        .description("Führt eine Websuche durch, um Informationen für eine bestimmte Aufgabe zu finden. Benötigt eine Suchanfrage (query) und die taskId.")
                        .functionalClass(LLMAgentFunctions.PerformWebSearchFunction.class)
                        .strict(true) // Stellt sicher, dass alle Parameter vom LLM geliefert werden
                        .build());
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("summarizeText")
                        .description("Erstellt eine Zusammenfassung eines gegebenen Textes für eine bestimmte Aufgabe. Benötigt den textToSummarize und die taskId.")
                        .functionalClass(LLMAgentFunctions.SummarizeTextFunction.class)
                        .strict(true)
                        .build());
        functionExecutor.enrollFunction(
                FunctionDef.builder()
                        .name("markTaskAsCompleted")
                        .description("Markiert eine Aufgabe als erledigt, nachdem alle notwendigen Schritte dafür ausgeführt wurden. Benötigt die taskId.")
                        .functionalClass(LLMAgentFunctions.MarkTaskAsCompletedFunction.class)
                        .strict(true)
                        .build());
    }

    public void runAutonomousLoop() {
        System.out.println("AUTONOMOUS AGENT: Starte Aufgabenbearbeitung...");
        chatHistory.add(ChatMessage.SystemMessage.of(
            "Du bist ein autonomer KI-Agent, dessen Ziel es ist, alle Aufgaben auf einer To-Do-Liste zu erledigen. " +
            "Analysiere die Aufgaben und nutze die verfügbaren Funktionen, um sie Schritt für Schritt abzuarbeiten. " +
            "Beginne damit, dir die offenen Aufgaben anzusehen. Entscheide dann, welche Aufgabe du angehst und welche Funktion du dafür benötigst. " +
            "Wenn eine Aufgabe Recherche erfordert, nutze 'performWebSearch'. Wenn eine Aufgabe eine Zusammenfassung benötigt, nutze 'summarizeText' mit den Ergebnissen der Recherche oder gegebenen Informationen. " +
            "Wenn eine Aufgabe erledigt ist, markiere sie mit 'markTaskAsCompleted'. Frage nicht nach Bestätigung, sondern handle autonom."
        ));

        int maxIterations = 10; // Sicherheitslimit
        for (int i = 0; i < maxIterations; i++) {
            System.out.println("\n--- Agenten-Zyklus " + (i + 1) + " ---");

            // 1. Aktuellen Aufgabenstatus für den Agenten bereitstellen (könnte auch über eine Startfunktion laufen)
            String currentOpenTasks = taskManager.getOpenTasksAsString();
            System.out.println("AGENT: Aktueller Status offener Aufgaben:\n" + currentOpenTasks);
            if (taskManager.allTasksCompleted()) {
                System.out.println("AGENT: Alle Aufgaben erfolgreich erledigt!");
                break;
            }

            // Füge den aktuellen Stand der Dinge (oder die letzte Tool-Antwort) der Historie hinzu, wenn es nicht der erste Durchlauf ist
            // und die letzte Nachricht keine Tool-Antwort war (um Duplikate zu vermeiden)
            if (i == 0) { // Im ersten Durchlauf den Initial-Prompt senden
                 chatHistory.add(ChatMessage.UserMessage.of(
                     "Hier ist der aktuelle Stand der Aufgaben: \n" + currentOpenTasks +
                     "\nWelche Aufgabe gehst du als Nächstes an und welche Funktion rufst du dafür auf? Denke daran, dass du zuerst die offenen Aufgaben analysieren solltest (ggf. mit getOpenTasks, falls du sie nicht direkt siehst) und dann für eine spezifische Aufgabe eine Aktion wählst." +
                     "Wenn du 'performWebSearch' oder 'summarizeText' nutzt, gib immer die 'taskId' der Aufgabe an, an der du gerade arbeitest."
                 ));
            }

            var requestBuilder = ChatRequest.builder()
                    .model(MODEL_NAME)
                    .messages(chatHistory)
                    .tools(functionExecutor.getToolFunctions()) // Wichtig: Tool-Definitionen mitgeben
                    .temperature(0.2); // Niedrige Temperatur für deterministischere Entscheidungen

            System.out.println("AGENT: Sende Anfrage an LLM...");
            var response = openAIClient.chatCompletions().create(requestBuilder.build()).join();
            ChatMessage responseMessage = response.firstMessage();
            chatHistory.add(responseMessage); // Antwort des Assistenten zur Historie hinzufügen

            var toolCalls = response.firstMessage().getToolCalls();
            if (toolCalls != null && !toolCalls.isEmpty()) {
                System.out.println("AGENT: LLM möchte folgende Funktion(en) aufrufen:");
                for (ToolCall toolCall : toolCalls) {
                    String functionName = toolCall.getFunction().getName();
                    String arguments = toolCall.getFunction().getArguments();
                    System.out.println("  Funktion: " + functionName);
                    System.out.println("  Argumente: " + arguments);

                    try {
                        // Funktion ausführen
                        // Simuliere die Ausführung der Funktion
                        Object executionResult = null;
                        if (functionName.equals("getOpenTasks")) {
                            executionResult = taskManager.getOpenTasksAsString();
                        } else if (functionName.equals("performWebSearch")) {
                            // Parse JSON arguments
                            JsonNode argsNode = objectMapper.readTree(arguments);
                            String query = argsNode.get("query").asText();
                            String taskId = argsNode.get("taskId").asText();

                            // Call the actual method
                            String result = taskManager.performWebSearch(query);
                            taskManager.addInfoToTask(taskId, result);
                            executionResult = result;
                        } else if (functionName.equals("summarizeText")) {
                            // Parse JSON arguments
                            JsonNode argsNode = objectMapper.readTree(arguments);
                            String textToSummarize = argsNode.get("textToSummarize").asText();
                            String taskId = argsNode.get("taskId").asText();

                            // Call the actual method
                            executionResult = taskManager.summarizeText(textToSummarize, taskId);
                        } else if (functionName.equals("markTaskAsCompleted")) {
                            // Parse JSON arguments
                            JsonNode argsNode = objectMapper.readTree(arguments);
                            String taskId = argsNode.get("taskId").asText();

                            // Call the actual method
                            executionResult = taskManager.markTaskAsCompleted(taskId);
                        }
                        String resultJson;
                        if (executionResult instanceof String) {
                            // Wenn es ein einfacher String ist, verpacken wir ihn ggf. in ein JSON-Objekt, wie es die API erwartet
                            resultJson = objectMapper.writeValueAsString(Map.of("result", executionResult));
                        } else {
                            resultJson = objectMapper.writeValueAsString(executionResult);
                        }


                        System.out.println("  Ergebnis der Funktion '" + functionName + "': " + resultJson);
                        // Tool-Ergebnis zur Historie hinzufügen für den nächsten LLM-Aufruf
                        chatHistory.add(ChatMessage.ToolMessage.of(resultJson, toolCall.getId()));

                    } catch (Exception e) {
                        System.err.println("Fehler bei der Ausführung der Funktion " + functionName + ": " + e.getMessage());
                        chatHistory.add(ChatMessage.ToolMessage.of("Fehler bei Ausführung: " + e.getMessage(), toolCall.getId()));
                    }
                }
            } else {
                // Der LLM hat direkt geantwortet, ohne eine Funktion aufzurufen
                String content = response.firstContent();
                System.out.println("AGENT: Antwort vom LLM ohne Funktionsaufruf: " + content);
                // Hier könnte der Agent entscheiden, ob das Ziel erreicht ist oder ob er eine neue Runde starten soll.
                // Wenn der LLM z.B. sagt "Alle Aufgaben sind erledigt", könnten wir hier prüfen.
                if (content != null && content.toLowerCase().contains("alle aufgaben erledigt") && taskManager.allTasksCompleted()) {
                     System.out.println("AGENT: LLM bestätigt Abschluss. Beende Loop.");
                     break;
                }
                 // Füge eine neue UserMessage hinzu, um den LLM zu einem weiteren Schritt zu bewegen, falls er nicht von sich aus einen ToolCall gemacht hat
                 // oder wenn er nur Text geantwortet hat, der nicht das Ende signalisiert.
                 chatHistory.add(ChatMessage.UserMessage.of(
                     "Das war deine letzte Antwort: '" + content + "'. Was ist der nächste Schritt, um die verbleibenden Aufgaben zu erledigen? Nutze eine Funktion." +
                     " Aktuelle offene Aufgaben:\n" + taskManager.getOpenTasksAsString()
                 ));
            }

            // Kurze Pause für Lesbarkeit
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!taskManager.allTasksCompleted()) {
            System.out.println("\nAGENT: Maximale Iterationen erreicht oder Loop vorzeitig beendet. Nicht alle Aufgaben erledigt.");
        }
        System.out.println("\n--- Endgültiger Aufgabenstatus ---");
        System.out.println(taskManager.getOpenTasksAsString());
    }


    public static void main(String[] args) {
        // WICHTIG: Stellen Sie sicher, dass die Umgebungsvariable OPENAI_API_KEY gesetzt ist!
        try {
            AutonomousLLMAgent agent = new AutonomousLLMAgent();
            agent.runAutonomousLoop();
        } catch (IllegalStateException e) {
            System.err.println("FEHLER: " + e.getMessage());
            System.err.println("Bitte stellen Sie sicher, dass die Umgebungsvariable OPENAI_API_KEY gesetzt ist.");
        }
    }
}
