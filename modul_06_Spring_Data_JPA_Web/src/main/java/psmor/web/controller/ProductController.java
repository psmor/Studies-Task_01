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

    @GetMapping(path = "findall")
    public ProductDtoResp getAll(){
        return productService.selectAllDto();
    }

    @GetMapping(path = "findid")
    public ProductDto getId(@RequestParam("id") Long id) {
        return productService.selectIdDto(id);
    }
}
