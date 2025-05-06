package psmor.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import psmor.web.enums.ProductType;

@Entity
@Table(name = "pproduct")
//@Table(name = "pproduct", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String account;
    private double balance;
    private ProductType productType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
}
