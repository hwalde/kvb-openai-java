package de.entwickler.training.exercise05;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

/**
 * Übung 5: Einfache Anweisung (gpt-4o-mini)
 *
 * Ziel: Eine klare, direkte Anweisung an das Modell geben und die Ausführung prüfen.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Formulierung einer klaren Handlungsaufforderung im User-Prompt
 *
 * Aufgabe:
 * 1. Erstelle ein ChatRequest-Objekt mit einer klaren Anweisung
 *    (z.B. "Übersetze den folgenden Satz ins Englische: Die Sonne scheint.")
 * 2. Sende die Anfrage an die OpenAI API
 * 3. Extrahiere und gib die Antwort aus
 * 4. Prüfe, ob die Übersetzung korrekt ist
 *
 * Hinweis: Diese Übung zeigt, wie man klare Anweisungen an das Modell gibt
 * und wie das Modell diesen folgt.
 */
public class SimpleInstruction {

    public static void main(String[] args) {
        // TODO: Erstelle einen Prompt mit einer klaren Anweisung
        // Beispiel: "Übersetze den folgenden Satz ins Englische: Die Sonne scheint."
        String prompt = "Übersetze den folgenden Satz ins Englische: Die Sonne scheint.";

        // TODO: Erstelle die Chat-Request-Parameter mit dem Prompt
        // Hinweis: Verwende ChatRequest.builder()...


        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Sende die Anfrage an die OpenAI API
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere und gib die Antwort aus
        // Hinweis: Verwende chatResponse.firstContent()


        // TODO: Füge eine Notiz hinzu, die prüft, ob die Anweisung korrekt befolgt wurde
        System.out.println("\nPrüfung:");
        // Dein Kommentar hier...

    }
}
