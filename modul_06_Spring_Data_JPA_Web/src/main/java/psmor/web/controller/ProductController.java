package psmor.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import psmor.web.dto.ProductBalanceDto;
import psmor.web.dto.ProductDto;
import psmor.web.dto.ProductDtoResp;
import psmor.web.dto.ProductExceptionDto;
import psmor.web.service.ProductService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping                     //Вызов: GET /v1/product
    public ProductDtoResp getAll(){
        return productService.selectAllProducts();
    }

    @GetMapping(path = "id")       //Вызов: GET /v1/product/id?id=1
    public ProductDto getId(@RequestParam("id") Long id) {
        return productService.selectProductById(id);
    }

    @GetMapping(path = "/{id}")    //Вызов: GET /v1/product/1
    public ProductDto getPathId(@PathVariable("id") Long id) {
        return productService.selectProductById(id);
    }

    @PostMapping(path = "/balanse")
    public String cangeBalance(@RequestBody ProductBalanceDto reqDto) {
        productService.updateBalance(reqDto);
        return "success";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    public ProductExceptionDto handleException(NoSuchElementException e) {    // Ошибка поиска продукта
        return new ProductExceptionDto("Продукт не найден.", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ProductExceptionDto handleException(Exception e) {                // Другие ошибки
        return new ProductExceptionDto("Ошибка.", e.getMessage());
    }
}
