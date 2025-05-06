package de.entwickler.training.exercise52;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.image.ImageRequest;

/**
 * Übung 52: Bild mit Stilvorgabe (dall-e-2)
 *
 * Ziel: Den generierten Bildstil durch Hinzufügen von Stilbeschreibungen im Prompt beeinflussen.
 *
 * Modell: dall-e-2
 *
 * Wichtige Parameter/Konzepte:
 * - Formulierung des Prompts zur Angabe eines Stils
 * - Vergleich verschiedener Stile
 *
 * Aufgabe:
 * 1. Erstelle mehrere Prompts für dasselbe Motiv, aber mit unterschiedlichen Stilvorgaben
 *    (z.B. "Eine rote Katze sitzt auf einem Baumstumpf, im Stil von Ölgemälde" oder
 *    "... im Cyberpunk-Stil")
 * 2. Sende die Anfragen an die DALL-E 2 API
 * 3. Gib die URLs der generierten Bilder aus
 * 4. Vergleiche die Ergebnisse
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key konfiguriert ist.
 */
public class ImageWithStyle {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere mehrere Prompts für dasselbe Motiv, aber mit unterschiedlichen Stilvorgaben
        // Beispiel:
        // String basePrompt = "Eine rote Katze sitzt auf einem Baumstumpf";
        // String[] styles = {
        //     ", im Stil eines Ölgemäldes",
        //     ", im Cyberpunk-Stil",
        //     ", im Stil eines Aquarells",
        //     ", im Stil eines Comics"
        // };

        // TODO: Generiere Bilder für jeden Stil und gib die URLs aus
        // Beispiel:
        // for (String style : styles) {
        //     String prompt = basePrompt + style;
        //     
        //     var imageRequest = ImageRequest.builder()
        //             .model("dall-e-2")
        //             .prompt(prompt)
        //             .n(1)
        //             .size("512x512")
        //             .build();
        //
        //     var imageResponse = openAIClient.images().create(imageRequest).join();
        //
        //     System.out.println("Prompt: " + prompt);
        //     System.out.println("URL: " + imageResponse.getData().get(0).getUrl());
        //     System.out.println();
        // }

        System.out.println("Prompt 1: "); // Dein Prompt hier
        System.out.println("URL 1: "); // Dein Code hier
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Prompt 2: "); // Dein Prompt hier
        System.out.println("URL 2: "); // Dein Code hier
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Prompt 3: "); // Dein Prompt hier
        System.out.println("URL 3: "); // Dein Code hier
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Vergleich der Stile:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Wie gut hat DALL-E 2 die verschiedenen Stile umgesetzt?
        // - Welcher Stil hat am besten funktioniert?
        // - Gibt es Unterschiede in der Qualität oder Detailgenauigkeit zwischen den Stilen?
    }
}
