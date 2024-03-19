package pl.jlabs.example.dto;

import jakarta.validation.constraints.NotNull;

public record QuestionAnswerDto(@NotNull String content) { }
