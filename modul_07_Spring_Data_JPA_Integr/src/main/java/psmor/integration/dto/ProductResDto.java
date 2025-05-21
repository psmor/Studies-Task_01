package psmor.integration.dto;

public record ProductResDto(
    Long id,
    String account,
    double balance,
    String productType
) {}
