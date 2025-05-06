package de.entwickler.training.exercise10;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;
import io.github.sashirestela.openai.domain.chat.Chat;
/**
 * Übung 10: Erste Anfrage (Internes Modell - Qwen)
 *
 * Ziel: Erfolgreiche Verbindung und API-Anfrage an das interne Qwen-3 32B Modell.
 *
 * Modell: Qwen-3 32B
 *
 * Wichtige Parameter/Konzepte:
 * - Konfiguration der base_url, api_key, modelId in config.properties
 * - Senden einer einfachen Anfrage
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Qwen-3 32B Modell:
 *    - base_url: "http://kvbai01-abn-lan:8080/v1"
 *    - api_key: "NONE"
 *    - modelId: "/models/qwen3-32b-fp8"
 * 2. Erstelle einen OpenAIClient mit diesen Konfigurationen
 * 3. Sende eine einfache Anfrage an das Modell
 * 4. Gib die Antwort aus
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class FirstInternalModelRequest {
    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8080/v1
        // api_key=NONE
        // TODO: Erstelle einen OpenAIClient mit den Konfigurationen für das Qwen-3 32B Modell
        // Hinweis: Du kannst den vorhandenen OpenAIClientService verwenden oder einen neuen Client erstellen
        // Hier ist ein Beispiel, wie du einen neuen Client erstellen könntest:
        // SimpleOpenAI client = SimpleOpenAI.builder()
        //         .baseUrl("http://kvbai01-abn-lan:8080/v1")
        //         .apiKey("NONE")
        //         .build();
        // Oder verwende den vorhandenen Client, wenn die config.properties korrekt konfiguriert ist:
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // Ein einfacher Test-Prompt
        String prompt = "Wer bist du?";
        // TODO: Erstelle die Chat-Completion-Parameter mit dem Prompt und der Model-ID
        // Hinweis: Verwende die Model-ID "/models/qwen3-32b-fp8"
        // Beispiel:
        // var chatRequest = ChatRequest.builder()
        //         .model("/models/qwen3-32b-fp8")
        //         .message(UserMessage.of(prompt))
        //         .build();

        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // Beispiel:
        // var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Qwen-3 32B Modell:");
        // Dein Code hier...
        // Beispiel:
        // System.out.println(chatResponse.firstContent());
        // TODO: Füge einen Kommentar hinzu, der die Antwort analysiert
        System.out.println("\nAnalyse der Antwort:");
        // Dein Kommentar hier...
    }
}
