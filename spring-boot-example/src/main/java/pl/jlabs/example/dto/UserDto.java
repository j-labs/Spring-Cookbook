package pl.jlabs.example.dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(
        Integer id,
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull CompanyDto company,
        String createdBy
) {}
