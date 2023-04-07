package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "suppliers")
public class Supplier {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String cuit;

    private String address;

    private float coordX;

    private float coordY;

    public Supplier() {
    }

    public Supplier(String name, String cuit, String address, float coordX, float coordY) {
        this.name = name;
        this.cuit = cuit;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuil() {
        return cuit;
    }

    public void setCuil(String cuil) {
        this.cuit = cuil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
