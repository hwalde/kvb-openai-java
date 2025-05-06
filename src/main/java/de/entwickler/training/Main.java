package de.entwickler.training;

import de.entwickler.training.util.OpenAIClientService;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;


public class Main {
    public static void main(String[] args) {
        ChatRequest chatRequest = ChatRequest.builder()
                .model("gpt-4o-mini")
                .message(ChatMessage.UserMessage.of("Say this is a test"))
                .build();
        var futureChat = OpenAIClientService.getInstance().getOpenAIClient().chatCompletions().create(chatRequest);
        var chatResponse = futureChat.join();
        chatResponse.getChoices().forEach(choice -> {
            System.out.println(choice.getMessage().getRole());
            System.out.println(choice.getMessage().getContent());
            System.out.println();
        });
        // System.out.println(chatResponse.firstContent());
    }
}