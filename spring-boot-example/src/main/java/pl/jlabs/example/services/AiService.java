package pl.jlabs.example.services;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import pl.jlabs.example.dto.QuestionAnswerDto;

@Service
@RequiredArgsConstructor
public class AiService {
    private final OpenAiChatClient openAiChatClient;

    public QuestionAnswerDto answer(QuestionAnswerDto question) {
        var prompt = new Prompt(
                question.content(),
                OpenAiChatOptions.builder()
                        .withModel("gpt-3.5-turbo")
                        .withMaxTokens(100)
                        .withTemperature(0.5f)
                        .build()
        );
        var response = openAiChatClient.call(prompt);
        var answerContent = response.getResult().getOutput().getContent();
        return new QuestionAnswerDto(answerContent);
    }
}
