package de.entwickler.training.exercise51;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.image.ImageRequest;
import io.github.sashirestela.openai.domain.image.Size;

/**
 * Übung 51: Einfache Bildgenerierung (dall-e-2)
 *
 * Ziel: Ein Bild mithilfe der DALL-E 2 API von OpenAI basierend auf einer einfachen
 * Textbeschreibung generieren.
 *
 * Modell: dall-e-2 (über OpenAI API)
 *
 * Wichtige Parameter/Konzepte:
 * - Bildgenerierungs-Endpunkt (ImageGenerationRequest)
 * - prompt-Parameter mit der Bildbeschreibung
 * - Model-ID dall-e-2
 * - Verarbeiten der Antwort (ImageResult), die URLs zu den generierten Bildern enthält
 *
 * Aufgabe:
 * 1. Erstelle einen Prompt für die Bildgenerierung (z.B. "Eine rote Katze sitzt auf einem Baumstumpf.")
 * 2. Sende die Anfrage an die DALL-E 2 API
 * 3. Gib die URL des generierten Bildes aus
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key konfiguriert ist.
 */
public class SimpleImageGeneration {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere einen Prompt für die Bildgenerierung
        // Beispiel: "Eine rote Katze sitzt auf einem Baumstumpf."
        String prompt = ""; // Dein Prompt hier

        // TODO: Erstelle die Bildgenerierungs-Parameter
//        ImageRequest.builder()
//                 .model("dall-e-2")
//                 .prompt(prompt)
//                 .n(1) // Anzahl der zu generierenden Bilder
//                 .size(Size.X256) // Bildgröße (256x256, 512x512, 1024x1024)
//                 .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.images().create(imageRequest).join();

        // TODO: Extrahiere und gib die URL des generierten Bildes aus
        System.out.println("Prompt: " + prompt);
        System.out.println("\nURL des generierten Bildes:");
        // Dein Code hier...
        // System.out.println(imageResponse.getData().get(0).getUrl());

        // Optional: Lade das Bild herunter oder zeige es an
        System.out.println("\nOptional: Anleitung zum Herunterladen des Bildes");
        System.out.println("1. Kopiere die URL");
        System.out.println("2. Öffne die URL in einem Browser");
        System.out.println("3. Speichere das Bild mit Rechtsklick -> 'Bild speichern unter...'");
    }
}
