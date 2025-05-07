package de.entwickler.training.agent;

import java.util.LinkedHashMap; // LinkedHashMap, um Reihenfolge beizubehalten
import java.util.Map;
import java.util.stream.Collectors;

public class TaskManager {
    private Map<String, ToDoTask> tasks = new LinkedHashMap<>();

    public TaskManager() {
        // Beispielaufgaben
        tasks.put("task1", new ToDoTask("task1", "Finde heraus, wer der aktuelle Bundeskanzler von Deutschland ist.", "RECHERCHE"));
        tasks.put("task2", new ToDoTask("task2", "Erstelle eine kurze Zusammenfassung über die Funktionsweise von KI-Agenten (max 3 Sätze). Die Infos dazu sind in 'relatedInfo' nach der Recherche zu 'KI-Agenten Funktionsweise'.", "ZUSAMMENFASSUNG"));
        tasks.put("task3", new ToDoTask("task3", "Bestätige, dass alle wichtigen Vorbereitungen für das morgige Meeting getroffen wurden.", "EINFACH"));
    }

    public String getOpenTasksAsString() {
        String openTasks = tasks.values().stream()
                .filter(t -> !t.isCompleted())
                .map(ToDoTask::toString)
                .collect(Collectors.joining("\n"));
        return openTasks.isEmpty() ? "Alle Aufgaben sind erledigt." : openTasks;
    }

    public String performWebSearch(String query) {
        System.out.println("TASK_MANAGER: Führe Websuche für '" + query + "' durch...");
        // Simulation
        if (query.toLowerCase().contains("bundeskanzler")) {
            return "Die aktuelle Websuche ergibt: Olaf Scholz ist der Bundeskanzler von Deutschland (Stand Mai 2025).";
        } else if (query.toLowerCase().contains("ki-agenten funktionsweise")) {
            return "KI-Agenten nehmen ihre Umgebung wahr, treffen Entscheidungen basierend auf Zielen und führen Aktionen aus. Sie können autonom und adaptiv sein.";
        }
        return "Keine spezifischen Ergebnisse für '" + query + "' gefunden.";
    }

    public String summarizeText(String textToSummarize, String taskId) {
        System.out.println("TASK_MANAGER: Erstelle Zusammenfassung für Task '" + taskId + "'...");
        if (textToSummarize == null || textToSummarize.isEmpty()) {
            return "Kein Text zum Zusammenfassen vorhanden.";
        }
        // Simulation einer Zusammenfassung
        String summary = "Zusammenfassung: " + textToSummarize.substring(0, Math.min(textToSummarize.length(), 100)) + "...";
        ToDoTask task = tasks.get(taskId);
        if (task != null) {
            task.setRelatedInfo(summary); // Speichere die Zusammenfassung bei der Aufgabe
        }
        return summary;
    }

    public String markTaskAsCompleted(String taskId) {
        ToDoTask task = tasks.get(taskId);
        if (task != null) {
            task.setCompleted(true);
            System.out.println("TASK_MANAGER: Aufgabe '" + taskId + " - " + task.getDescription() + "' als erledigt markiert.");
            return "Aufgabe " + taskId + " erfolgreich als erledigt markiert.";
        }
        return "Aufgabe " + taskId + " nicht gefunden.";
    }

    public boolean allTasksCompleted() {
        return tasks.values().stream().allMatch(ToDoTask::isCompleted);
    }

    // Hilfsmethode, um Infos für den LLM zu setzen, z.B. Suchergebnisse
    public void addInfoToTask(String taskId, String info) {
        ToDoTask task = tasks.get(taskId);
        if (task != null) {
            task.setRelatedInfo(info);
            System.out.println("TASK_MANAGER: Info zu Task '" + taskId + "' hinzugefügt/aktualisiert.");
        }
    }
}