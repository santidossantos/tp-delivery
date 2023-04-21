package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "description", length = 150)
    private String description;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(fetch = EAGER, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    public Item() {}

    public Item(Order order, Product product, int quantity, String description){
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
