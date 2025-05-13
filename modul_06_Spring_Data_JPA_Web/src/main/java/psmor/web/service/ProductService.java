package psmor.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import psmor.web.dto.ProductDto;
import psmor.web.dto.ProductDtoResp;
import psmor.web.entity.Product;
import psmor.web.repository.ProductRepository;
import psmor.web.utils.MappingUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MappingUtils mappingUtils;

    public ProductDtoResp selectAllProducts() {
        List<ProductDto> productsDto =
                productRepository.findAll().stream()                                       //создали из листа стрим
                        .map(product -> mappingUtils.productToProductDto(product)) //оператором из streamAPI map, использовали для каждого элемента метод mapToProductDto из класса MappingUtils
                        .collect(Collectors.toList());                                     //превратили стрим обратно в коллекцию, а точнее в лист

        return new ProductDtoResp(productsDto);
    }

    public ProductDto selectProductById(Long id) {
        // 1-й способ.
//        return mappingUtils.productToProductDto(          //в метод mapToProductDto
//                productRepository.findById(id)                //поместили результат поиска по id
//                        .orElse(new Product())               //если ни чего не нашли, то вернем пустой entity
//        );

        // 2-й способ. Оставлю закомментированным, для наглядности.
        Optional<Product> productResault = productRepository.findById(id);
        Product product = null;
        ProductDto productDto = null;
        MappingUtils mappingUtils = new MappingUtils();
        if ( productResault.isPresent() ){
            product = productResault.get();
            productDto = mappingUtils.productToProductDto(product);
        } else {
            log.info("Пользователь с id="+id+" не найден");
        }
        return productDto;
    }
}
