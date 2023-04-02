package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;

    private float price;

    private Date lastPriceUpdateDate;

    private float weight;

    private String description;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(mappedBy = "products")
    private List<ProductType> types;

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<ProductType> getTypes() {
        return types;
    }

    public void setTypes(List<ProductType> types) {
        this.types = types;
    }

    public Date getLastPriceUpdateDate() {
        return lastPriceUpdateDate;
    }

    public void setLastPriceUpdateDate(Date lastPriceUpdateDate) {
        this.lastPriceUpdateDate = lastPriceUpdateDate;
    }

    public Long getId() {
        return null;
    }

    public String getName() {
        return null;
    }
}
