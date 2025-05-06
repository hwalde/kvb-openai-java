package de.entwickler.training.exercise54;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.image.ImageRequest;

/**
 * Übung 54: Prompt-Variationen für Bilder (dall-e-2)
 *
 * Ziel: Verstehen, wie kleine Änderungen im Prompt zu unterschiedlichen Bildergebnissen führen können.
 *
 * Modell: dall-e-2
 *
 * Wichtige Parameter/Konzepte:
 * - Sensitivität des Modells gegenüber der Prompt-Formulierung
 * - Einfluss der Wortstellung und Betonung auf das generierte Bild
 *
 * Aufgabe:
 * 1. Erstelle mehrere leicht variierte Prompts für dasselbe grundlegende Motiv
 *    (z.B. verschiedene Formulierungen für "Ein Astronaut reitet auf einem Pferd auf dem Mond")
 * 2. Generiere Bilder für jeden Prompt
 * 3. Gib die URLs der generierten Bilder aus
 * 4. Vergleiche die Ergebnisse und analysiere, wie die Prompt-Variationen das Bild beeinflusst haben
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key konfiguriert ist.
 */
public class PromptVariations {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere mehrere leicht variierte Prompts für dasselbe grundlegende Motiv
        // Beispiel:
        // String[] prompts = {
        //     "Ein Astronaut reitet auf einem Pferd auf dem Mond.",
        //     "Ein Astronaut reitet auf einem Pferd, Hintergrund ist der Mond.",
        //     "Ein Pferd mit einem Astronautenhelm auf dem Mond."
        // };

        // TODO: Generiere Bilder für jeden Prompt und gib die URLs aus
        // Beispiel:
        // for (String prompt : prompts) {
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
        
        System.out.println("Analyse der Prompt-Variationen:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Wie haben sich die Bilder je nach Prompt-Formulierung verändert?
        // - Welche Elemente des Prompts wurden priorisiert oder betont?
        // - Welche Formulierung hat am besten funktioniert, um die gewünschte Szene darzustellen?
    }
}
