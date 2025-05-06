package de.entwickler.training.exercise45;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartImageUrl;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartImageUrl.ImageUrl;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartText;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.util.List;

/**
 * Übung 45: Vision (Bild-URL, gpt-4o-mini)
 *
 * Ziel: Die Vision-Fähigkeiten von gpt-4o-mini nutzen, um eine Frage zu einem Bild
 * zu beantworten, das über eine URL bereitgestellt wird.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Struktur von ChatMessage für multimodale Eingaben (Liste von Content-Objekten: Typ "text" und Typ "image_url")
 * - image_url Objekt mit url-Feld
 *
 * Aufgabe:
 * 1. Erstelle einen Prompt mit einer Frage zu einem Bild und der URL des Bildes
 * 2. Sende die Anfrage an die OpenAI API
 * 3. Gib die Antwort aus
 *
 * Hinweis: Verwende eine frei zugängliche Bild-URL, z.B. von Wikipedia Commons.
 */
public class VisionWithImageUrl {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere eine Bild-URL (z.B. von Wikipedia Commons)
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

        // TODO: Erstelle die Chat-Request-Parameter mit dem multimodalen Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(userMessage)
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort von gpt-4o-mini zur Bild-URL:");
        // Dein Code hier...
        // System.out.println(chatResponse.firstContent());

        System.out.println("\n----------------------------------------\n");

        // Optional: Stelle eine spezifischere Frage zum selben Bild
        System.out.println("Optional: Antwort auf eine spezifischere Frage zum selben Bild:");
        // Dein Code hier...
    }
}
