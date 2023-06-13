package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "last_price_update_date")
    private Date lastPriceUpdateDate;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "description", length = 150)
    private String description;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(cascade = {}, fetch = LAZY)
    @JoinTable(
            name = "product_product_type",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_type_id")
    )
    private List<ProductType> types;

    public Product() {}

    public Product(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.description = description;
        this.supplier = supplier;
        this.types = types;
        this.lastPriceUpdateDate = new Date();
    }

    public Product(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) {
        this(name, price, weight, description, supplier, types);
        this.lastPriceUpdateDate = lastPriceUpdateDate;
    }

    public void updateProductPrice(float price) {
        this.price = price;
        this.lastPriceUpdateDate = new Date();
    }

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


    public String getName() {
        return name;
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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}