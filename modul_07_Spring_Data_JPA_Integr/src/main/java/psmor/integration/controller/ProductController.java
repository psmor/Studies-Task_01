package psmor.integration.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psmor.integration.dto.BalanceChangeReqDto;
import psmor.integration.dto.BalanceChangeResDto;
import psmor.integration.dto.ProductResDto;
import psmor.integration.dto.ProductExceptionDto;
import psmor.integration.exceptions.BalanceErrorException;
import psmor.integration.service.ProductService;

@RestController
@RequestMapping(value = "/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "/{id}")    //Вызов: GET /v1/product/1
    public ProductResDto getPathId(@PathVariable("id") Long id) {
        return productService.selectProductById(id);
    }

    @PostMapping(path = "/balanse")
    public BalanceChangeResDto cangeBalance(@RequestBody BalanceChangeReqDto reqDto) {
        return productService.cangeBalance(reqDto);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BalanceErrorException.class)
    public ProductExceptionDto handleException(BalanceErrorException e) {
        return new ProductExceptionDto("Проверка на остаток:", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ProductExceptionDto handleException(Exception e) {
        return new ProductExceptionDto("Произошла ошибка:", e.getMessage());
    }
}
