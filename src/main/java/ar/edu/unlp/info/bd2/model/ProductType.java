package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product_types")
public class ProductType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    public ProductType() {}

    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private String description;

    @ManyToMany(mappedBy = "types")
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
