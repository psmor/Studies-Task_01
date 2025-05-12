package psmor.web.dto;

import java.util.List;

public record UserDto(
        Long id,
        String username,
        List<ProductDto> products
) {}
