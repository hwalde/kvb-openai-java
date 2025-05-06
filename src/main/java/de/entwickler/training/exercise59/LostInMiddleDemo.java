package de.entwickler.training.exercise59;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.chat.ChatMessage.UserMessage;

/**
 * Übung 59: Kontextfenster (Lost in Middle Demo, Mistral Large)
 *
 * Ziel: Das Phänomen "Lost in the Middle" demonstrieren, bei dem LLMs Schwierigkeiten
 * haben können, Informationen aus der Mitte eines sehr langen Kontextes abzurufen.
 *
 * Modell: Mistral Large (hat ein großes Kontextfenster)
 *
 * Wichtige Parameter/Konzepte:
 * - Erstellung eines sehr langen Prompts
 * - Platzierung einer spezifischen, einzigartigen Information an verschiedenen Stellen
 * - Gezielte Frage nach dieser Information
 *
 * Aufgabe:
 * 1. Konfiguriere die config.properties für das Mistral Large Modell (wie in Übung 11)
 * 2. Erstelle einen langen Fülltext (mehrere tausend Tokens)
 * 3. Platziere eine spezifische Information (z.B. "Die magische Zahl ist XY789Z.")
 *    an drei Stellen: am Anfang, in der Mitte und am Ende des Fülltextes
 * 4. Stelle eine Frage nach dieser Information am Ende des Prompts
 * 5. Beobachte, ob das Modell die Information findet und von welcher Stelle
 *
 * WICHTIG: Für dieses Modell muss die base_url und der api_key in src/main/resources/config.properties
 * korrekt gesetzt sein. Der Zugriff ist nur über VPN möglich.
 */
public class LostInMiddleDemo {

    public static void main(String[] args) {
        // WICHTIG: Stelle sicher, dass die config.properties korrekt konfiguriert ist:
        // base_url=http://kvbai03-abn-lan:8080/v1
        // api_key=none
        var openAIClient = OpenAIClientService.getInstance().getOpenAIClient();

        // Die spezifische Information, die wir im Text platzieren und später abfragen
        String magicInfo = "Die magische Zahl ist XY789Z.";
        
        // TODO: Erstelle einen langen Fülltext
        // Hinweis: Verwende die bereitgestellte Methode generateFillerText
        // Beispiel:
        // String fillerText = generateFillerText(2000); // Etwa 2000 Wörter

        // TODO: Platziere die spezifische Information an drei Stellen
        // Beispiel:
        // String promptWithInfoAtBeginning = magicInfo + "\n\n" + fillerText + "\n\nWas ist die magische Zahl, die im Text erwähnt wurde?";
        // String promptWithInfoInMiddle = fillerText.substring(0, fillerText.length() / 2) + "\n\n" + magicInfo + "\n\n" + 
        //                                fillerText.substring(fillerText.length() / 2) + "\n\nWas ist die magische Zahl, die im Text erwähnt wurde?";
        // String promptWithInfoAtEnd = fillerText + "\n\n" + magicInfo + "\n\nWas ist die magische Zahl, die im Text erwähnt wurde?";

        // TODO: Teste die drei Varianten nacheinander
        // Beispiel:
        // testPrompt(openAIClient, "Anfang", promptWithInfoAtBeginning);
        // testPrompt(openAIClient, "Mitte", promptWithInfoInMiddle);
        // testPrompt(openAIClient, "Ende", promptWithInfoAtEnd);

        System.out.println("Lost in the Middle Demo:");
        System.out.println("Spezifische Information: " + magicInfo);
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Test 1: Information am Anfang des Textes");
        // Dein Code hier...
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Test 2: Information in der Mitte des Textes");
        // Dein Code hier...
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Test 3: Information am Ende des Textes");
        // Dein Code hier...
        
        System.out.println("\n----------------------------------------\n");
        
        System.out.println("Analyse des 'Lost in the Middle'-Phänomens:");
        // Dein Kommentar hier...
        // Beispiel:
        // - Konnte das Modell die Information in allen drei Fällen finden?
        // - Gab es Unterschiede in der Genauigkeit oder Sicherheit der Antwort?
        // - Was bedeutet dies für die Gestaltung von Prompts mit langen Kontexten?
    }
    
    /**
     * Testet einen Prompt und gibt die Antwort aus
     * 
     * @param openAIClient Der OpenAI-Client
     * @param position Die Position der Information (für die Ausgabe)
     * @param prompt Der zu testende Prompt
     */
    private static void testPrompt(io.github.sashirestela.openai.SimpleOpenAI openAIClient, String position, String prompt) {
        System.out.println("Test mit Information am " + position + ":");
        
        try {
            var chatRequest = ChatRequest.builder()
                    .model("/models/Mistral-Large-Instruct-2411-FP8")
                    .message(UserMessage.of(prompt))
                    .build();
            
            var chatResponse = openAIClient.chatCompletions().create(chatRequest).join();
            
            System.out.println("Antwort: " + chatResponse.firstContent());
        } catch (Exception e) {
            System.err.println("Fehler beim Testen: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Generiert einen langen Fülltext mit der angegebenen Anzahl von Wörtern
     * 
     * @param wordCount Die ungefähre Anzahl der Wörter
     * @return Der generierte Fülltext
     */
    private static String generateFillerText(int wordCount) {
        // Beispielparagraphen, die wiederholt werden, um einen langen Text zu erzeugen
        String[] paragraphs = {
            "Künstliche Intelligenz (KI) ist ein Teilgebiet der Informatik, das sich mit der Automatisierung intelligenten Verhaltens befasst. Der Begriff ist schwer zu definieren, da es keine allgemein anerkannte formale Definition gibt, was Intelligenz ist. Im Allgemeinen bezeichnet KI den Versuch, menschenähnliche Entscheidungsstrukturen in einem nicht-lebenden System zu implementieren, insbesondere in Computersystemen.",
            
            "Maschinelles Lernen ist ein Teilbereich der künstlichen Intelligenz, der sich mit Algorithmen und Methoden befasst, die es Computern ermöglichen, aus Erfahrungen zu lernen und ihre Leistung zu verbessern, ohne explizit programmiert zu werden. Es basiert auf der Idee, dass Systeme aus Daten lernen, Muster identifizieren und Entscheidungen mit minimaler menschlicher Intervention treffen können.",
            
            "Deep Learning ist ein Teilbereich des maschinellen Lernens, der auf künstlichen neuronalen Netzen mit mehreren Schichten basiert. Diese Methode hat in den letzten Jahren zu bedeutenden Fortschritten in Bereichen wie Bilderkennung, Sprachverarbeitung und Spielen geführt. Deep-Learning-Systeme können komplexe Muster in großen Datenmengen erkennen und haben in einigen Aufgaben menschenähnliche oder sogar übermenschliche Leistungen erzielt.",
            
            "Natural Language Processing (NLP) ist ein Bereich der künstlichen Intelligenz, der sich mit der Interaktion zwischen Computern und menschlicher Sprache befasst. Ziel ist es, Computer in die Lage zu versetzen, menschliche Sprache zu verstehen, zu interpretieren und zu generieren. NLP-Anwendungen umfassen Spracherkennung, maschinelle Übersetzung, Sentimentanalyse und Chatbots.",
            
            "Computer Vision ist ein interdisziplinäres Feld, das sich damit befasst, wie Computer hochwertige Informationen aus digitalen Bildern oder Videos gewinnen können. Es umfasst Methoden zum Erfassen, Verarbeiten, Analysieren und Verstehen von Bildern und im Allgemeinen hochdimensionalen Daten aus der realen Welt, um symbolische oder numerische Informationen zu produzieren.",
            
            "Reinforcement Learning ist ein Bereich des maschinellen Lernens, bei dem ein Agent lernt, in einer Umgebung zu handeln, um eine kumulative Belohnung zu maximieren. Im Gegensatz zum überwachten Lernen wird dem Agenten nicht gesagt, welche Aktionen er ausführen soll, sondern er muss selbst herausfinden, welche Aktionen die größte Belohnung bringen, indem er sie ausprobiert.",
            
            "Robotik ist ein Bereich der Technik und Wissenschaft, der sich mit dem Design, der Konstruktion, dem Betrieb und der Verwendung von Robotern sowie Computersystemen für deren Kontrolle, sensorisches Feedback und Informationsverarbeitung befasst. Diese Technologien befassen sich mit automatisierten Maschinen, die menschliche Handlungen ersetzen können.",
            
            "Ethik in der KI befasst sich mit moralischen Prinzipien, die das Verhalten von KI-Systemen leiten sollten. Wichtige Themen sind Fairness, Transparenz, Verantwortlichkeit, Datenschutz und die Auswirkungen von KI auf die Gesellschaft. Da KI-Systeme immer autonomer werden, wird die Frage, wie sie ethische Entscheidungen treffen sollten, immer wichtiger."
        };
        
        StringBuilder sb = new StringBuilder();
        int currentWordCount = 0;
        int targetWordCount = wordCount;
        
        // Wiederhole die Paragraphen, bis die gewünschte Wortanzahl erreicht ist
        while (currentWordCount < targetWordCount) {
            for (String paragraph : paragraphs) {
                sb.append(paragraph).append("\n\n");
                currentWordCount += paragraph.split("\\s+").length;
                
                if (currentWordCount >= targetWordCount) {
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}
