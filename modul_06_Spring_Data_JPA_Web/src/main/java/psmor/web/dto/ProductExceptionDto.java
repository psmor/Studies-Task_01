package psmor.web.dto;

import java.io.Serializable;

public record ProductExceptionDto(
        String reasonMessage,
        String exceptionMessage ) {
}
