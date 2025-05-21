package psmor.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import psmor.web.dto.ProductBalanceDto;
import psmor.web.dto.ProductDto;
import psmor.web.dto.ProductDtoResp;
import psmor.web.entity.Product;
import psmor.web.entity.User;
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
                        .map(product -> mappingUtils.productToProductDto(product)) //оператором из streamAPI map, использовали для каждого
                                                                                           // элемента метод mapToProductDto из класса MappingUtils
                        .collect(Collectors.toList());                                     //превратили стрим обратно в коллекцию, а точнее в лист

        return new ProductDtoResp(productsDto);
    }

    public Product selectById(Long id) {
        return productRepository.findById(id)   //поместили результат поиска по id
                .orElseThrow();                 //если ни чего не нашли, то выбрасываем исключение (NoSuchElementException - по умолчанию)
    }

    public ProductDto selectProductById(Long id) {
        // 1-й способ. Сомнительно, тогда вернём пустой объект
        return mappingUtils.productToProductDto( selectById(id) );   //в метод mapToProductDto
    }

    public void updateBalance(ProductBalanceDto productBalanceDto) {
        Product product = selectById(productBalanceDto.id());
        if (product != null) {
            product.setBalance(productBalanceDto.balance());
            productRepository.save(product);
        }
    }
}
