package de.entwickler.training.exercise46;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartImageUrl;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartImageUrl.ImageUrl;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartText;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.util.List;

/**
 * Übung 46: Vision (Bild-URL, Mistral Large)
 *
 * Ziel: Die Vision-Fähigkeiten des internen Mistral Large Modells testen und mit gpt-4o-mini vergleichen.
 *
 * Modell: Mistral Large
 *
 * Wichtige Parameter/Konzepte:
 * - Multimodale Eingabe für die spezifische API
 * - Konfiguration für Mistral Large (URL, Key, VPN)
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Verwende dieselbe Bild-URL und dieselbe Frage wie in Übung 45
 * 3. Sende die Anfrage an das Mistral Large Modell
 * 4. Gib die Antwort aus und vergleiche sie mit der Antwort von gpt-4o-mini
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class VisionWithImageUrlMistral {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai03-abn-lan:8080/v1
        // api_key=none
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Verwende dieselbe Bild-URL wie in Übung 45
        // Beispiel: "https://upload.wikimedia.org/wikipedia/commons/e/eb/Machu_Picchu%2C_Peru.jpg"
        String imageUrl = ""; // Deine Bild-URL hier

        // TODO: Erstelle einen multimodalen Prompt mit einer Frage zum Bild und der Bild-URL
        // Hinweis: Verwende ContentPartText für den Text und ContentPartImageUrl für das Bild
        // Beispiel:
        // List<io.github.sashirestela.openai.common.content.ContentPart> contentParts = List.of(
        //         ContentPartText.of("Was ist auf diesem Bild zu sehen?"),
        //         ContentPartImageUrl.of(ImageUrl.of(imageUrl))
        // );
        // 
        // UserMessage userMessage = UserMessage.of(contentParts);

        // TODO: Erstelle die Chat-Request-Parameter mit dem multimodalen Prompt und der Model-ID
        // Hinweis: Verwende die Model-ID "/models/Mistral-Large-Instruct-2411-FP8"
        // Beispiel:
        // var chatRequest = ChatRequest.builder()
        //         .model("/models/Mistral-Large-Instruct-2411-FP8")
        //         .message(userMessage)
        //         .build();

        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort vom Mistral Large Modell zur Bild-URL:");
        // Dein Code hier...
        // System.out.println(chatResponse.firstContent());

        System.out.println("\n----------------------------------------\n");

        System.out.println("Vergleich mit gpt-4o-mini:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Wie unterscheiden sich die Antworten in Bezug auf Detailgenauigkeit?
        // - Gibt es Unterschiede in der Beschreibung bestimmter Elemente des Bildes?
        // - Welches Modell liefert die bessere oder umfassendere Antwort?
    }
}
