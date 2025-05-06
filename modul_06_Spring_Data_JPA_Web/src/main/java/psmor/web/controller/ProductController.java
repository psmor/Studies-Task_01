package psmor.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psmor.web.entity.Product;
import psmor.web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "findall")
    public List<Product> getAll(){
        return productService.selectAll();
    }

    @GetMapping(path = "findid")
    public Product getId(@RequestParam("id") Long id) {
        return productService.selectId(id);
    }
}
