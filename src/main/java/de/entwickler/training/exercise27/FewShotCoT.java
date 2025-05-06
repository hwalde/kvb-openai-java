 
package de.entwickler.training.exercise27;

import de.entwickler.training.util.OpenAIClientService;
/**
 * Übung 27: Few-Shot CoT (Mistral Large)
 * 
 * Ziel: Mistral Large durch Beispiele mit expliziten Denkschritten anleiten,
 * komplexere, mehrstufige Aufgaben zu lösen.
 * 
 * Modell: Mistral Large
 * 
 * Wichtige Parameter/Konzepte:
 * - Bereitstellung von 1-2 Beispielen im Prompt, die nicht nur Input/Output,
 *   sondern auch die Zwischenschritte zeigen
 * 
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Erstelle einen Few-Shot CoT Prompt, der ein Beispiel mit expliziten Denkschritten enthält
 *    und dann eine neue, komplexere Aufgabe stellt
 * 3. Sende den Prompt an das Modell
 * 4. Prüfe, ob das Modell die Struktur mit den Schritten übernimmt und die Aufgabe korrekt löst
 * 
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class FewShotCoT {
    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai03-abn-lan:8080/v1
        // api_key=none
        // TODO: Erstelle einen OpenAIClient mit den Konfigurationen für das Mistral Large Modell
        // Hinweis: Du kannst den vorhandenen OpenAIClientService verwenden oder einen neuen Client erstellen
        // Hier ist ein Beispiel, wie du einen neuen Client erstellen könntest:
        // OpenAIClient client = OpenAIOkHttpClient.builder()
        //         .baseUrl("http://kvbai03-abn-lan:8080/v1")
        //         .apiKey("none")
        //         .build();
        // Oder verwende den vorhandenen Client, wenn die config.properties korrekt konfiguriert ist:
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();
        // Few-Shot CoT Prompt mit einem Beispiel und einer neuen Aufgabe
        String fewShotCoTPrompt = "Löse die Aufgabe Schritt für Schritt.\n\n" +
                "Aufgabe: Ein Auto fährt mit einer Geschwindigkeit von 60 km/h. Wie weit kommt es in 2,5 Stunden?\n" +
                "Lösungsschritte: \n" +
                "1. Ich muss die Strecke berechnen, die das Auto in 2,5 Stunden zurücklegt.\n" +
                "2. Die Formel lautet: Strecke = Geschwindigkeit × Zeit\n" +
                "3. Strecke = 60 km/h × 2,5 h\n" +
                "4. Strecke = 150 km\n" +
                "Antwort: Das Auto legt in 2,5 Stunden eine Strecke von 150 km zurück.\n\n" +
                "Aufgabe: Ein Schwimmbecken hat ein Volumen von 240 m³. Es wird mit einer Rate von 0,2 m³ pro Minute gefüllt. " +
                "Gleichzeitig verliert es durch ein Leck 0,05 m³ pro Minute. Wie lange dauert es, bis das Becken vollständig gefüllt ist?\n" +
                "Lösungsschritte:";
        // TODO: Erstelle die Chat-Completion-Parameter für den Few-Shot CoT Prompt
        // Hinweis: Verwende ChatRequest.builder()
        //         .addUserMessage(fewShotCoTPrompt)
        //         .model("/models/Mistral-Large-Instruct-2411-FP8")
        //         .build();
        // TODO: Sende die Anfrage an die API und speichere die Antwort
        // TODO: Extrahiere und gib die Antwort aus
        System.out.println("Antwort auf den Few-Shot CoT Prompt:");
        // Dein Code hier...
        System.out.println("\n----------------------------------------\n");
        // TODO: Prüfe, ob das Modell die Struktur mit den Schritten übernimmt und die Aufgabe korrekt löst
        System.out.println("Prüfung der Antwort:");
        // Dein Kommentar hier...
        // Hinweis: Die korrekte Antwort sollte etwa 1600 Minuten oder 26,67 Stunden sein
        // (Nettofüllrate = 0,2 - 0,05 = 0,15 m³/min, Zeit = 240 / 0,15 = 1600 min)
    }
}
