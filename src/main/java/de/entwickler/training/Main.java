package de.entwickler.training;

import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import de.entwickler.training.util.OpenAIClientManger;

public class Main {
    public static void main(String[] args) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage("Say this is a test")
                .model(ChatModel.GPT_4_1_MINI)
                .build();
        ChatCompletion chatCompletion = OpenAIClientManger.getInstance().getOpenAIClient().chat().completions().create(params);

        System.out.println(chatCompletion.choices().getFirst().message().content());
    }
}