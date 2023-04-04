package ar.edu.unlp.info.bd2.model;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String apartment;

    private float coordX;

    private float coordY;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Address() {}

    public Address(String name, String address, float coordX, float coordY, String description, Client client) {
        this.name = name;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
        this.description = description;
        this.client = client;
        this.client.addAddress(this); // Preguntar si es necesario para mantener la consistencia de la relacion
    }

    public Address(String name, String address, String apartment, float coordX, float coordY, String description, Client client) {
        this(name, address, coordX, coordY, description, client);
        this.apartment = apartment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
