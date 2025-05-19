package psmor.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psmor.web.dto.ProductDto;
import psmor.web.dto.ProductDtoResp;
import psmor.web.service.ProductService;

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
}
