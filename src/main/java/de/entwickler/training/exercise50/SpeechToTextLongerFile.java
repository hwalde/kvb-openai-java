package de.entwickler.training.exercise50;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.common.audio.InputAudioFormat;
import io.github.sashirestela.openai.domain.audio.TranscriptionRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Übung 50: STT längere Datei (whisper-1)
 *
 * Ziel: Die Fähigkeit von Whisper testen, auch längere Audiodateien zu verarbeiten.
 *
 * Modell: whisper-1
 *
 * Wichtige Parameter/Konzepte:
 * - AudioTranscriptionRequest
 * - Umgang mit potenziell längeren Verarbeitungszeiten
 * - Dateigrößenlimits der API beachten
 *
 * Aufgabe:
 * 1. Bereite eine längere Audiodatei vor (z.B. 1-2 Minuten)
 * 2. Implementiere das Einlesen und Kodieren der Audiodatei
 * 3. Erstelle eine Anfrage an die Whisper API
 * 4. Sende die Anfrage und gib den transkribierten Text aus
 * 5. Beobachte die Verarbeitungszeit und die Qualität der Transkription
 *
 * Hinweis: Stelle sicher, dass der OpenAI API-Key konfiguriert ist.
 * Beachte, dass die OpenAI API ein Limit für die Dateigröße hat (aktuell 25 MB).
 */
public class SpeechToTextLongerFile {

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Definiere den Pfad zu einer längeren lokalen Audiodatei (WAV oder MP3, 1-2 Minuten)
        // Beispiel: "path/to/your/longer_audio.mp3"
        String audioPath = ""; // Dein Audiopfad hier

        try {
            // Prüfe die Dateigröße
            Path path = Paths.get(audioPath);
            long fileSizeInBytes = Files.size(path);
            long fileSizeInMB = fileSizeInBytes / (1024 * 1024);
            
            System.out.println("Audiodatei: " + audioPath);
            System.out.println("Dateigröße: " + fileSizeInMB + " MB");
            
            if (fileSizeInMB > 25) {
                System.out.println("Warnung: Die Datei ist größer als 25 MB, was das aktuelle Limit der OpenAI API ist.");
                System.out.println("Die Anfrage wird wahrscheinlich fehlschlagen.");
                // In einer echten Anwendung würde hier die Datei aufgeteilt oder komprimiert werden
            }

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

            // Starte die Zeitmessung
            long startTime = System.currentTimeMillis();

            // TODO: Erstelle die Audio-Transkriptions-Parameter
            // Hinweis: Verwende TranscriptionRequest.builder()
            //         .model("whisper-1")
            //         .audio(base64Audio)
            //         .audioFormat(audioFormat)
            //         .build();

            // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
            // Hinweis: Verwende openAIClient.audios().create(audioRequest).join();

            // Beende die Zeitmessung
            long endTime = System.currentTimeMillis();
            long durationInSeconds = (endTime - startTime) / 1000;

            // TODO: Extrahiere und gib den transkribierten Text aus
            System.out.println("\nTranskribierter Text:");
            // Dein Code hier...
            // System.out.println(transcriptionResponse.getText());

            System.out.println("\nVerarbeitungszeit: " + durationInSeconds + " Sekunden");

            // Optional: Analysiere die Qualität der Transkription
            System.out.println("\nAnalyse der Transkriptionsqualität:");
            // Dein Kommentar hier...
            // Beispiel:
            // - Wie gut hat das Modell längere Sätze oder komplexere Ausdrücke erkannt?
            // - Gibt es Probleme mit bestimmten Wörtern oder Akzenten?
            // - Wie ist die Qualität im Vergleich zur kürzeren Audiodatei aus Übung 49?

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
