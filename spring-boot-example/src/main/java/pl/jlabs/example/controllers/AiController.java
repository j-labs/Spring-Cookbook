package pl.jlabs.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jlabs.example.dto.QuestionAnswerDto;
import pl.jlabs.example.services.AiService;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;

    @PostMapping("/question")
    public QuestionAnswerDto postQuestion(@Valid @RequestBody QuestionAnswerDto question) {
        return aiService.answer(question);
    }
}
