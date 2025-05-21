package psmor.integration.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductType {
    ACCOUNT("Счет"),
    CARD("Карта");
    private String name;
}
