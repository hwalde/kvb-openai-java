package de.entwickler.training.exercise56;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

import java.net.ConnectException;
import java.util.concurrent.CompletionException;

/**
 * Übung 56: Einfache Retry-Logik
 *
 * Ziel: Eine einfache Strategie implementieren, um fehlgeschlagene API-Anfragen
 * (insbesondere bei temporären Problemen wie Ratenlimits oder Netzwerk-Timeouts)
 * automatisch erneut zu versuchen.
 *
 * Modell: Beliebiges Modell, z.B. Mistral Large (interne Modelle könnten eher Timeouts haben)
 *
 * Wichtige Parameter/Konzepte:
 * - Schleifenkonstrukt (z.B. for oder while) um den try-catch-Block
 * - Zähler für Wiederholungsversuche
 * - Thread.sleep() für Wartezeiten zwischen Versuchen
 *
 * Aufgabe:
 * 1. Implementiere eine Methode mit Retry-Logik für API-Aufrufe
 * 2. Setze eine maximale Anzahl von Versuchen (z.B. 3)
 * 3. Füge Wartezeiten zwischen den Versuchen ein (z.B. 1-2 Sekunden)
 * 4. Teste die Implementierung mit einem einfachen API-Aufruf
 * 5. Optional: Simuliere einen temporären Fehler, um die Retry-Logik zu testen
 *
 * Hinweis: In einer echten Anwendung würde man einen exponentiellen Backoff verwenden
 * (d.h. die Wartezeit zwischen den Versuchen erhöhen), aber für diese Übung reicht
 * eine einfache konstante Wartezeit.
 */
public class RetryLogic {

    // Maximale Anzahl von Versuchen
    private static final int MAX_RETRIES = 3;
    
    // Wartezeit zwischen den Versuchen in Millisekunden
    private static final long RETRY_DELAY_MS = 2000; // 2 Sekunden

    public static void main(String[] args) {
        // Hier ist der OpenAI-Client bereits für dich eingerichtet
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Einfacher Prompt für einen API-Aufruf
        String prompt = "Hallo, wie geht es dir?";

        // TODO: Erstelle die Chat-Request-Parameter
        // Hinweis: Verwende ChatRequest.builder()
        //         .model("/models/Mistral-Large-Instruct-2411-FP8") // oder ein anderes Modell deiner Wahl
        //         .message(UserMessage.of(prompt))
        //         .build();

        // TODO: Rufe die Methode mit Retry-Logik auf
        // Beispiel:
        // String response = callWithRetry(openAIClient, chatRequest);
        // System.out.println("Antwort: " + response);

        System.out.println("API-Aufruf mit Retry-Logik:");
        // Dein Code hier...

        // Optional: Simuliere einen temporären Fehler
        System.out.println("\n----------------------------------------\n");
        System.out.println("Optional: Simulation eines temporären Fehlers");
        
        // Beispiel für die Simulation eines temporären Fehlers:
        // - Kommentiere den folgenden Code ein und passe ihn an, um einen temporären Fehler zu simulieren
        // - Zum Beispiel, indem du ein ungültiges Modell verwendest, das nach dem ersten Versuch korrigiert wird
        
        // simulateTemporaryError(openAIClient, prompt);
    }

    /**
     * Führt einen API-Aufruf mit Retry-Logik durch
     * 
     * @param openAIClient Der OpenAI-Client
     * @param chatRequest Die Chat-Request-Parameter
     * @return Die Antwort des Modells oder eine Fehlermeldung
     */
    private static String callWithRetry(io.github.sashirestela.openai.SimpleOpenAI openAIClient, ChatRequest chatRequest) {
        // TODO: Implementiere die Retry-Logik
        // Hier ist ein Beispiel für die Implementierung:
        
        int retries = 0;
        
        while (retries < MAX_RETRIES) {
            try {
                System.out.println("Versuch " + (retries + 1) + " von " + MAX_RETRIES);
                
                var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
                
                // Wenn der Aufruf erfolgreich war, gib die Antwort zurück
                System.out.println("Aufruf erfolgreich!");
                return chatResponse.firstContent();
                
            } catch (CompletionException e) {
                retries++;
                Throwable cause = e.getCause();
                
                // Prüfe, ob es sich um einen temporären Fehler handelt
                boolean isTemporaryError = isTemporaryError(cause);
                
                if (isTemporaryError && retries < MAX_RETRIES) {
                    // Bei temporären Fehlern: Warte und versuche es erneut
                    System.out.println("Temporärer Fehler: " + cause.getMessage());
                    System.out.println("Warte " + (RETRY_DELAY_MS / 1000) + " Sekunden vor dem nächsten Versuch...");
                    
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return "Fehler: Der Thread wurde unterbrochen.";
                    }
                } else {
                    // Bei permanenten Fehlern oder wenn die maximale Anzahl von Versuchen erreicht ist
                    return "Fehler nach " + retries + " Versuchen: " + cause.getMessage();
                }
            } catch (Exception e) {
                return "Allgemeiner Fehler: " + e.getMessage();
            }
        }
        
        return "Fehler: Maximale Anzahl von Versuchen (" + MAX_RETRIES + ") erreicht.";
    }

    /**
     * Prüft, ob es sich um einen temporären Fehler handelt
     * 
     * @param cause Die Ursache des Fehlers
     * @return true, wenn es sich um einen temporären Fehler handelt, sonst false
     */
    private static boolean isTemporaryError(Throwable cause) {
        // TODO: Implementiere die Prüfung auf temporäre Fehler
        // Hier ist ein Beispiel für die Implementierung:
        
        // Netzwerkfehler sind oft temporär
        if (cause instanceof ConnectException) {
            return true;
        }
        
        // Rate Limit (429) und Server-Fehler (5xx) sind oft temporär
        String message = cause.getMessage();
        return message != null && (message.contains("429") || message.contains("500") || 
                message.contains("502") || message.contains("503") || message.contains("504"));
    }

    /**
     * Simuliert einen temporären Fehler, der nach dem ersten Versuch behoben wird
     * 
     * @param openAIClient Der OpenAI-Client
     * @param prompt Der Prompt für die Anfrage
     */
    private static void simulateTemporaryError(io.github.sashirestela.openai.SimpleOpenAI openAIClient, String prompt) {
        // Diese Methode simuliert einen temporären Fehler, indem sie beim ersten Aufruf
        // ein ungültiges Modell verwendet und beim zweiten Aufruf ein gültiges Modell
        
        final String[] models = {
            "ungültiges-modell", // Ungültiges Modell für den ersten Versuch
            "/models/Mistral-Large-Instruct-2411-FP8" // Gültiges Modell für den zweiten Versuch
        };
        
        int retries = 0;
        
        while (retries < MAX_RETRIES) {
            try {
                System.out.println("Simulierter Versuch " + (retries + 1) + " von " + MAX_RETRIES);
                
                // Wähle das Modell basierend auf dem aktuellen Versuch
                String model = retries < models.length ? models[retries] : models[models.length - 1];
                System.out.println("Verwende Modell: " + model);
                
                var chatRequest = ChatRequest.builder()
                        .model(model)
                        .message(UserMessage.of(prompt))
                        .build();
                
                var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
                
                // Wenn der Aufruf erfolgreich war, gib die Antwort aus und beende die Schleife
                System.out.println("Aufruf erfolgreich!");
                System.out.println("Antwort: " + chatResponse.firstContent());
                break;
                
            } catch (Exception e) {
                retries++;
                
                if (retries < MAX_RETRIES) {
                    // Simuliere eine Wartezeit
                    System.out.println("Simulierter Fehler: " + e.getMessage());
                    System.out.println("Warte " + (RETRY_DELAY_MS / 1000) + " Sekunden vor dem nächsten Versuch...");
                    
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        System.out.println("Fehler: Der Thread wurde unterbrochen.");
                        break;
                    }
                } else {
                    System.out.println("Fehler nach " + retries + " Versuchen: " + e.getMessage());
                }
            }
        }
    }
}
