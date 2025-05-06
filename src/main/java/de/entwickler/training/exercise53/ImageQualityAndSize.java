package de.entwickler.training.exercise53;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.image.ImageRequest;

/**
 * Übung 53: Bildqualität/-größe (dall-e-2)
 *
 * Ziel: Die Parameter zur Steuerung von Bildgröße und (implizit) Qualität bei DALL-E 2
 * kennenlernen und anwenden.
 *
 * Modell: dall-e-2
 *
 * Wichtige Parameter/Konzepte:
 * - size-Parameter im ImageGenerationRequest (mögliche Werte für DALL-E 2: "256x256", "512x512", "1024x1024")
 * - n-Parameter (Anzahl der Bilder)
 *
 * Aufgabe:
 * 1. Erstelle einen Prompt für ein Motiv (z.B. "Ein blauer Hund auf einer Wiese")
 * 2. Generiere Bilder in verschiedenen Größen (256x256, 512x512, 1024x1024)
 * 3. Gib die URLs der generierten Bilder aus
 * 4. Vergleiche die Ergebnisse in Bezug auf Qualität und Detailgenauigkeit
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key konfiguriert ist.
 */
public class ImageQualityAndSize {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere einen Prompt für die Bildgenerierung
        // Beispiel: "Ein blauer Hund auf einer Wiese"
        String prompt = ""; // Dein Prompt hier

        // TODO: Definiere verschiedene Bildgrößen
        // Beispiel:
        // String[] sizes = {"256x256", "512x512", "1024x1024"};

        // TODO: Generiere Bilder in verschiedenen Größen und gib die URLs aus
        // Beispiel:
        // for (String size : sizes) {
        //     var imageRequest = ImageRequest.builder()
        //             .model("dall-e-2")
        //             .prompt(prompt)
        //             .n(1)
        //             .size(size)
        //             .build();
        //     
        //     var imageResponse = openAIClient.images().create(imageRequest).join();
        //     
        //     System.out.println("Prompt: " + prompt);
        //     System.out.println("Größe: " + size);
        //     System.out.println("URL: " + imageResponse.getData().get(0).getUrl());
        //     System.out.println();
        // }

        System.out.println("Prompt: " + prompt);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Größe: 256x256");
        System.out.println("URL: "); // Dein Code hier
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Größe: 512x512");
        System.out.println("URL: "); // Dein Code hier
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Größe: 1024x1024");
        System.out.println("URL: "); // Dein Code hier
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Vergleich der Bildgrößen und Qualität:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Wie unterscheiden sich die Bilder in Bezug auf Detailgenauigkeit?
        // - Ist die höhere Auflösung den potenziell höheren Preis wert?
        // - Gibt es Unterschiede in der Komposition oder im Stil zwischen den verschiedenen Größen?
    }
}
