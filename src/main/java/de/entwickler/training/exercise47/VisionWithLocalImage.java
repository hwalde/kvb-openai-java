package de.entwickler.training.exercise47;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartImageUrl;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartImageUrl.ImageUrl;
import io.github.sashirestela.openai.common.content.ContentPart.ContentPartText;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;
import io.github.sashirestela.openai.support.Base64Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

/**
 * Übung 47: Vision (Lokales Bild, gpt-4o-mini)
 *
 * Ziel: Ein lokal gespeichertes Bild analysieren, indem es als Base64-kodierter String
 * an gpt-4o-mini gesendet wird.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Einlesen einer lokalen Bilddatei in Java
 * - Kodierung der Bilddaten als Base64-String
 * - Multimodale ChatMessage mit Content-Typ "image_url", wobei die URL das Format
 *   data:image/jpeg;base64,{base64_string} hat
 *
 * Aufgabe:
 * 1. Bereite eine lokale Bilddatei vor (z.B. JPG, PNG)
 * 2. Implementiere das Einlesen und Base64-Kodieren der Bilddatei
 * 3. Erstelle einen multimodalen Prompt mit einer Frage zum Bild und dem Base64-kodierten Bild
 * 4. Sende die Anfrage an die OpenAI API
 * 5. Gib die Antwort aus
 *
 * Hinweis: Die Methode zum Base64-Kodieren ist bereits implementiert.
 */
public class VisionWithLocalImage {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere den Pfad zu einer lokalen Bilddatei
        // Beispiel: "path/to/your/image.jpg"
        String imagePath = ""; // Dein Bildpfad hier

        try {
            // TODO: Lese die Bilddatei ein und kodiere sie als Base64-String
            // Hinweis: Verwende die bereitgestellte Methode encodeImageToBase64
            // Beispiel:
            // String base64Image = encodeImageToBase64(imagePath);
            // String dataUri = "data:image/jpeg;base64," + base64Image;
            
            // Alternativ kannst du auch die Hilfsmethode aus der simple-openai Bibliothek verwenden:
            // String dataUri = Base64Util.encode(imagePath, Base64Util.MediaType.IMAGE);

            // TODO: Erstelle einen multimodalen Prompt mit einer Frage zum Bild und dem Base64-kodierten Bild
            // Hinweis: Verwende ContentPartText für den Text und ContentPartImageUrl für das Bild
            // Beispiel:
            // List<io.github.sashirestela.openai.common.content.ContentPart> contentParts = List.of(
            //         ContentPartText.of("Beschreibe die Hauptmerkmale dieses Bildes."),
            //         ContentPartImageUrl.of(ImageUrl.of(dataUri))
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
            System.out.println("Antwort von gpt-4o-mini zum lokalen Bild:");
            // Dein Code hier...
            // System.out.println(chatResponse.firstContent());

        } catch (Exception e) {
            System.err.println("Fehler beim Verarbeiten des Bildes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Liest eine Bilddatei ein und kodiert sie als Base64-String
     * 
     * @param imagePath Der Pfad zur Bilddatei
     * @return Der Base64-kodierte String der Bilddatei
     * @throws IOException Wenn die Datei nicht gelesen werden kann
     */
    private static String encodeImageToBase64(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
