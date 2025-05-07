package de.entwickler.training.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.github.sashirestela.openai.common.function.Functional;

// Diese Klasse dient als Container für die Funktionslogik, die vom FunctionExecutor aufgerufen wird.
// Die eigentliche Implementierung der Logik liegt im TaskManager.
public class LLMAgentFunctions {

    private TaskManager taskManager;

    public LLMAgentFunctions(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    // Diese Klasse wird vom FunctionExecutor verwendet, um die Funktion getOpenTasks zu definieren
    public static class GetOpenTasksFunction implements Functional {
        // Keine Parameter für diese Funktion
        @Override
        public Object execute() {
            // Die tatsächliche Logik wird im Agenten-Loop gehandhabt,
            // da der TaskManager außerhalb des FunctionExecutors existiert.
            // Hier geben wir nur eine Bestätigung oder könnten den TaskManager injizieren,
            // aber es ist einfacher, die Logik im Hauptloop zu halten.
            // Für dieses Beispiel wird der TaskManager direkt im Haupt-Agenten-Loop verwendet.
            // Der FunctionExecutor benötigt jedoch eine Methode, die er aufrufen kann.
            // Wir geben hier nur ein Dummy-Ergebnis zurück, da der eigentliche Abruf
            // vor dem LLM-Aufruf im Agenten-Loop stattfindet oder der LLM die Funktion
            // "anfordert" und der Agent sie dann extern ausführt.

            // Besser: Der TaskManager wird hier direkt verwendet.
            return AutonomousLLMAgent.getTaskManagerForFunctions().getOpenTasksAsString();
        }
    }

    public static class PerformWebSearchFunction implements Functional {
        @JsonPropertyDescription("Die Suchanfrage, die an die Websuche gesendet werden soll, um Informationen für eine Aufgabe zu finden.")
        @JsonProperty(required = true)
        public String query;

        @JsonPropertyDescription("Die ID der Aufgabe, für die diese Websuche durchgeführt wird.")
        @JsonProperty(required = true)
        public String taskId;

        @Override
        public Object execute() {
            String result = AutonomousLLMAgent.getTaskManagerForFunctions().performWebSearch(query);
            AutonomousLLMAgent.getTaskManagerForFunctions().addInfoToTask(taskId, result); // Speichere Ergebnis bei der Aufgabe
            return result;
        }
    }

    public static class SummarizeTextFunction implements Functional {
        @JsonPropertyDescription("Der Text, der zusammengefasst werden soll. Oft das Ergebnis einer vorherigen Websuche.")
        @JsonProperty(required = true)
        public String textToSummarize;

        @JsonPropertyDescription("Die ID der Aufgabe, für die diese Zusammenfassung erstellt wird.")
        @JsonProperty(required = true)
        public String taskId;

        @Override
        public Object execute() {
            return AutonomousLLMAgent.getTaskManagerForFunctions().summarizeText(textToSummarize, taskId);
        }
    }

    public static class MarkTaskAsCompletedFunction implements Functional {
        @JsonPropertyDescription("Die ID der Aufgabe, die als erledigt markiert werden soll.")
        @JsonProperty(required = true)
        public String taskId;

        @Override
        public Object execute() {
            return AutonomousLLMAgent.getTaskManagerForFunctions().markTaskAsCompleted(taskId);
        }
    }
}