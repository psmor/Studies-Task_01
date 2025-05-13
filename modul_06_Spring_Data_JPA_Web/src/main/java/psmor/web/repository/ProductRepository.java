package psmor.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psmor.web.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
