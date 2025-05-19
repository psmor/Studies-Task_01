package psmor.web.utils;

import org.springframework.stereotype.Service;
import psmor.web.dto.ProductDto;
import psmor.web.dto.UserDto;
import psmor.web.entity.Product;
import psmor.web.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingUtils {

    public ProductDto productToProductDto(Product product) {
        ProductDto productDtoResp = new ProductDto(
                product.getId(),
                product.getAccount(),
                product.getBalance(),
                product.getProductType()
                );
        return productDtoResp;
    }

    public UserDto userToUserDto(User user) {
        // Получаем все продукты у пользователя
        List<Product> products = user.getProductList();
        // Собираем DTO продуктов
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(productToProductDto(product));
        }
        // Собираем DTO пользователя
        UserDto userDtoResp = new UserDto(
             user.getId(),
             user.getUsername(),
                productsDto
        );
        return userDtoResp;
    }
}
