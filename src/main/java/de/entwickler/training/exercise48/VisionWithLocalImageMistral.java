package de.entwickler.training.exercise48;

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
 * Übung 48: Vision (Lokales Bild, Mistral Small)
 *
 * Ziel: Die Vision-Fähigkeiten des kleineren internen Mistral Small Modells mit einem
 * lokalen Bild testen.
 *
 * Modell: Mistral Small
 *
 * Wichtige Parameter/Konzepte:
 * - Base64-Kodierung
 * - Multimodale Eingabe für die spezifische API
 * - Konfiguration für Mistral Small (URL, Key, VPN)
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Small Modell (wie in Übung 12)
 * 2. Verwende dasselbe lokale Bild wie in Übung 47
 * 3. Implementiere das Einlesen und Base64-Kodieren der Bilddatei
 * 4. Erstelle einen multimodalen Prompt mit einer Frage zum Bild und dem Base64-kodierten Bild
 * 5. Sende die Anfrage an das Mistral Small Modell
 * 6. Gib die Antwort aus und vergleiche sie mit den Antworten von gpt-4o-mini und Mistral Large
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class VisionWithLocalImageMistral {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai01-abn-lan:8090/v1
        // api_key=None
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Verwende dasselbe lokale Bild wie in Übung 47
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

            // TODO: Erstelle die Chat-Request-Parameter mit dem multimodalen Prompt und der Model-ID
            // Hinweis: Verwende die Model-ID "/models/Mistral-Small-3.1-24B-Instruct-2503/"
            // Beispiel:
            // var chatRequest = ChatRequest.builder()
            //         .model("/models/Mistral-Small-3.1-24B-Instruct-2503/")
            //         .message(userMessage)
            //         .build();

            // TODO: Sende die Anfrage an die API und speichere die Antwort
            // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

            // TODO: Extrahiere und gib die Antwort aus
            System.out.println("Antwort vom Mistral Small Modell zum lokalen Bild:");
            // Dein Code hier...
            // System.out.println(chatResponse.firstContent());

            System.out.println("\n----------------------------------------\n");

            System.out.println("Vergleich mit gpt-4o-mini und Mistral Large:");
            // Dein Kommentar hier...
            // Beispiel:
            // - Wie unterscheiden sich die Antworten in Bezug auf Detailgenauigkeit?
            // - Gibt es Unterschiede in der Beschreibung bestimmter Elemente des Bildes?
            // - Welches Modell liefert die beste oder umfassendste Antwort?

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
