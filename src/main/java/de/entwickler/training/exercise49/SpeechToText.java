package de.entwickler.training.exercise49;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.audio.InputAudioFormat;
import io.github.sashirestela.openai.domain.audio.TranscriptionRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Übung 49: Speech-to-Text (STT, whisper-1)
 *
 * Ziel: Eine kurze Audiodatei mithilfe der Whisper API von OpenAI in Text umwandeln.
 *
 * Modell: whisper-1 (über OpenAI API)
 *
 * Wichtige Parameter/Konzepte:
 * - Audio-Transkriptions-Endpunkt der OpenAI API (AudioTranscriptionRequest)
 * - Bereitstellung der Audiodatei (z.B. als Base64-String)
 * - Model-ID whisper-1
 *
 * Aufgabe:
 * 1. Bereite eine kurze Audiodatei vor (z.B. WAV oder MP3, wenige Sekunden gesprochener Text)
 * 2. Implementiere das Einlesen und Kodieren der Audiodatei
 * 3. Erstelle eine Anfrage an die Whisper API
 * 4. Sende die Anfrage und gib den transkribierten Text aus
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key konfiguriert ist.
 */
public class SpeechToText {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere den Pfad zu einer lokalen Audiodatei (WAV oder MP3)
        // Beispiel: "path/to/your/audio.mp3"
        String audioPath = ""; // Dein Audiopfad hier

        try {
            // TODO: Lese die Audiodatei ein und kodiere sie als Base64-String
            // Hinweis: Verwende die bereitgestellte Methode encodeAudioToBase64
            // Beispiel:
            // String base64Audio = encodeAudioToBase64(audioPath);
            
            // Alternativ kannst du auch die Hilfsmethode aus der simple-openai Bibliothek verwenden:
            // String base64Audio = io.github.sashirestela.openai.support.Base64Util.encode(audioPath, null);

            // TODO: Bestimme das Audioformat basierend auf der Dateiendung
            // Beispiel:
            // InputAudioFormat audioFormat = audioPath.toLowerCase().endsWith(".mp3") ? 
            //         InputAudioFormat.MP3 : InputAudioFormat.WAV;

            // TODO: Erstelle die Audio-Transkriptions-Parameter
            // Hinweis: Verwende TranscriptionRequest.builder()
            //         .model("whisper-1")
            //         .audio(base64Audio)
            //         .audioFormat(audioFormat)
            //         .build();

            // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
            // var transcriptionResponse = openAIClient.audios().create(audioRequest).join();

            // TODO: Extrahiere und gib den transkribierten Text aus
            System.out.println("Transkribierter Text:");
            // Dein Code hier...
            // System.out.println(transcriptionResponse.getText());

        } catch (Exception e) {
            System.err.println("Fehler beim Verarbeiten der Audiodatei: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Liest eine Audiodatei ein und kodiert sie als Base64-String
     * 
     * @param audioPath Der Pfad zur Audiodatei
     * @return Der Base64-kodierte String der Audiodatei
     * @throws IOException Wenn die Datei nicht gelesen werden kann
     */
    private static String encodeAudioToBase64(String audioPath) throws IOException {
        Path path = Paths.get(audioPath);
        byte[] audioBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(audioBytes);
    }
}
