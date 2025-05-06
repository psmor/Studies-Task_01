package psmor.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import psmor.web.entity.Product;
import psmor.web.entity.User;
import psmor.web.repository.ProductRepository;
import psmor.web.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> selectAll() {
        List<Product> product = productRepository.findAll();
        return product;
    }

    public Product selectId(Long id) {
        Optional<Product> res = productRepository.findById(id);
        Product product = null;
        if ( res.isPresent() ){
            product = res.get();
        } else {
            log.info("Пользователь с id="+id+" не найден");
        }
        return product;

    }
}
