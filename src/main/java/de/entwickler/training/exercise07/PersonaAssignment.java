package de.entwickler.training.exercise07;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;


/**
 * Übung 7: Persona zuweisen (User Prompt, gpt-4o-mini)
 *
 * Ziel: Lernen, wie man dem Modell durch eine Anweisung im User-Prompt eine bestimmte
 * Rolle oder Perspektive zuweist.
 *
 * Modell: gpt-4o-mini
 *
 * Wichtige Parameter/Konzepte:
 * - Explizite Rollenzuweisung im Prompt-Text
 *
 * Aufgabe:
 * 1. Erstelle zwei verschiedene Prompts:
 *    a) Einen einfachen Prompt ohne Persona: "Was ist Java?"
 *    b) Einen Prompt mit Persona: "Antworte wie ein Pirat: Was ist Java?"
 * 2. Sende beide Prompts an die OpenAI API
 * 3. Vergleiche die Antworten und beobachte, wie die Persona die Antwort beeinflusst
 *
 * Hinweis: Diese Übung zeigt, wie man durch einfache Anweisungen im Prompt
 * das Verhalten und den Stil des Modells beeinflussen kann.
 */
public class PersonaAssignment {

    public static void main(String[] args) {
        // Prompt ohne Persona
        String simplePrompt = "Was ist Java?";

        // Prompt mit Persona
        String personaPrompt = "Antworte wie ein Pirat: Was ist Java?";

        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // TODO: Erstelle die Chat-Request-Parameter für den einfachen Prompt
        // Hinweis: Verwende ChatRequest.builder()...


        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();


        // TODO: Extrahiere und gib die Antwort auf den einfachen Prompt aus
        System.out.println("Antwort auf den einfachen Prompt:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        // TODO: Erstelle die Chat-Request-Parameter für den Prompt mit Persona


        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort


        // TODO: Extrahiere und gib die Antwort auf den Prompt mit Persona aus
        System.out.println("Antwort auf den Prompt mit Persona:");
        // Dein Code hier...

        System.out.println("\n----------------------------------------\n");

        // TODO: Füge einen Vergleich der beiden Antworten hinzu
        System.out.println("Vergleich der Antworten:");
        // Dein Kommentar hier...
    }
}
