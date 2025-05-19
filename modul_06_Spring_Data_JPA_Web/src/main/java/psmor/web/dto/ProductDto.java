package psmor.web.dto;
import psmor.web.enums.ProductType;

public record ProductDto(
    Long id,
    String account,
    double balance,
    ProductType productType
) {}
