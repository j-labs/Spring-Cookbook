package pl.jlabs.example.dto;

public record CompanyDto(
        Integer id,
        String companyName,
        String createdBy
) {}
