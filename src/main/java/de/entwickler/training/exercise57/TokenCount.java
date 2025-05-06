package de.entwickler.training.exercise57;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

/**
 * Übung 57: Kostenabschätzung (Token Count)
 *
 * Ziel: Ein Bewusstsein für die Kosten von LLM-API-Aufrufen entwickeln, indem die
 * Anzahl der verbrauchten Tokens geschätzt wird.
 *
 * Modell: Relevant für OpenAI-Modelle (gpt-4o-mini, dall-e-2, whisper-1), aber auch zur
 * Abschätzung der Ressourcennutzung interner Modelle nützlich
 *
 * Wichtige Parameter/Konzepte:
 * - Tokens (Eingabe/Prompt-Tokens, Ausgabe/Completion-Tokens)
 * - Grobe Schätzung (z.B. 1 Token ≈ 4 Zeichen in Englisch)
 * - Abrufen der Token-Nutzung aus der API-Antwort (im usage-Objekt bei OpenAI)
 * - Berechnung der Kosten basierend auf den Preisen pro Token für das jeweilige Modell
 *
 * Aufgabe:
 * 1. Implementiere eine einfache Methode zur Schätzung der Tokenanzahl eines Textes
 * 2. Sende eine Anfrage an die OpenAI API und rufe die tatsächliche Token-Nutzung ab
 * 3. Vergleiche die geschätzte mit der tatsächlichen Tokenanzahl
 * 4. Berechne die geschätzten Kosten für die Anfrage
 *
 * Hinweis: Die tatsächlichen Kosten hängen vom verwendeten Modell ab. Aktuelle Preise
 * findest du auf der OpenAI-Website.
 */
public class TokenCount {

    // Ungefähre Preise pro 1000 Tokens (Stand: 2023, in USD)
    // Diese Werte können sich ändern, aktuelle Preise findest du auf der OpenAI-Website
    private static final double GPT_4O_MINI_INPUT_PRICE_PER_1K = 0.15;
    private static final double GPT_4O_MINI_OUTPUT_PRICE_PER_1K = 0.60;

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Beispieltext für die Token-Schätzung
        String prompt = "Erkläre mir das Konzept der künstlichen Intelligenz in einfachen Worten. " +
                "Was ist KI, wie funktioniert sie und welche Anwendungen gibt es heute schon? " +
                "Gib mir auch ein paar Beispiele für KI-Systeme, die wir im Alltag nutzen.";

        // TODO: Schätze die Anzahl der Tokens im Prompt
        // Hinweis: Verwende die bereitgestellte Methode estimateTokenCount
        // Beispiel:
        // int estimatedTokens = estimateTokenCount(prompt);
        // System.out.println("Geschätzte Anzahl der Tokens im Prompt: " + estimatedTokens);

        // TODO: Erstelle die Chat-Request-Parameter
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("gpt-4o-mini")
        //         .message(UserMessage.of(prompt))
        //         .build();

        // TODO: Sende die Anfrage an die OpenAI API und speichere die Antwort
        // Hinweis: Verwende openAIClient.chatCompletions().create(chatRequest).join();

        // TODO: Rufe die tatsächliche Token-Nutzung aus der Antwort ab
        // Beispiel:
        // var usage = chatResponse.getUsage();
        // int promptTokens = usage.getPromptTokens();
        // int completionTokens = usage.getCompletionTokens();
        // int totalTokens = usage.getTotalTokens();
        // 
        // System.out.println("Tatsächliche Token-Nutzung:");
        // System.out.println("- Prompt-Tokens: " + promptTokens);
        // System.out.println("- Completion-Tokens: " + completionTokens);
        // System.out.println("- Gesamt-Tokens: " + totalTokens);

        // TODO: Vergleiche die geschätzte mit der tatsächlichen Tokenanzahl
        // Beispiel:
        // double accuracy = (double) estimatedTokens / promptTokens * 100;
        // System.out.println("Genauigkeit der Schätzung: " + String.format("%.2f", accuracy) + "%");

        // TODO: Berechne die geschätzten Kosten für die Anfrage
        // Beispiel:
        // double inputCost = promptTokens / 1000.0 * GPT_4O_MINI_INPUT_PRICE_PER_1K;
        // double outputCost = completionTokens / 1000.0 * GPT_4O_MINI_OUTPUT_PRICE_PER_1K;
        // double totalCost = inputCost + outputCost;
        // 
        // System.out.println("Geschätzte Kosten:");
        // System.out.println("- Input: $" + String.format("%.6f", inputCost));
        // System.out.println("- Output: $" + String.format("%.6f", outputCost));
        // System.out.println("- Gesamt: $" + String.format("%.6f", totalCost));

        System.out.println("Prompt: " + prompt);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Token-Schätzung und tatsächliche Nutzung:");
        // Dein Code hier...
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Kostenabschätzung:");
        // Dein Code hier...
    }

    /**
     * Schätzt die Anzahl der Tokens in einem Text
     * 
     * @param text Der zu schätzende Text
     * @return Die geschätzte Anzahl der Tokens
     */
    private static int estimateTokenCount(String text) {
        // TODO: Implementiere eine einfache Methode zur Schätzung der Tokenanzahl
        // Hier ist ein Beispiel für die Implementierung:
        
        // Einfache Schätzung: 1 Token ≈ 4 Zeichen in Englisch
        // Dies ist eine grobe Schätzung und kann je nach Sprache und Inhalt variieren
        
        if (text == null || text.isEmpty()) {
            return 0;
        }
        
        // Zähle die Anzahl der Zeichen (ohne Leerzeichen)
        int charCount = text.replaceAll("\\s+", "").length();
        
        // Zähle die Anzahl der Wörter
        int wordCount = text.split("\\s+").length;
        
        // Gewichtete Schätzung: Berücksichtige sowohl Zeichen als auch Wörter
        // Diese Formel ist eine Annäherung und kann angepasst werden
        return (int) Math.ceil(charCount / 4.0 + wordCount / 2.0);
    }
}
