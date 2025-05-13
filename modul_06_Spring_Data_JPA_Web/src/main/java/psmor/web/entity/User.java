package psmor.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
//@Table(name = "users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    // fetch = FetchType.EAGER - Тащить связанные записи
    // fetch = FetchType.LAZY - Не тащить связанные записи. Тогда на поле нужно поставить @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product-list_id")
    @JsonManagedReference
    //@JsonIgnore                        // Не показывать в Json
    private List<Product> productList;
}
