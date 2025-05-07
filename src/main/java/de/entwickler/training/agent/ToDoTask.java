package de.entwickler.training.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToDoTask {
    private String id;
    private String description;
    private boolean completed;
    private String type; // z.B. "RECHERCHE", "ZUSAMMENFASSUNG", "EINFACH"
    private String relatedInfo; // z.B. Suchergebnisse oder Text für Zusammenfassung

    @JsonCreator // Wichtig für Deserialisierung, falls benötigt, hier aber primär für manuelle Erstellung
    public ToDoTask(@JsonProperty("id") String id,
                    @JsonProperty("description") String description,
                    @JsonProperty("type") String type) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.completed = false;
        this.relatedInfo = null;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public String getType() { return type; }
    public String getRelatedInfo() { return relatedInfo; }
    public void setRelatedInfo(String relatedInfo) { this.relatedInfo = relatedInfo; }

    @Override
    public String toString() {
        return "Task[ID=" + id + ", Type=" + type + ", Desc=" + description +
               ", Completed=" + completed +
               (relatedInfo != null ? ", InfoLen=" + relatedInfo.length() : "") + "]";
    }
}