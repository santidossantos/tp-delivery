package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private int number;

    @Column(name = "date_of_order", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateOfOrder;

    @Column(name = "comments", length = 100)
    private String comments;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @Column(name = "delivered")
    private boolean delivered;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "delivery_man_id")
    private DeliveryMan deliveryMan;

    @ManyToOne(cascade = PERSIST, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "order", cascade = ALL, fetch = LAZY)
    private Qualification qualification;

    @OneToOne(cascade = ALL, fetch = LAZY)
    private Address address;

    @OneToMany(mappedBy = "order", cascade = ALL, fetch = LAZY)
    private List<Item> items;

    public Order() {}

    public Order(int number, Date dateOfOrder, String comments, Client client, Address address) {
        this.number = number;
        this.dateOfOrder = dateOfOrder;
        this.comments = comments;
        this.client = client;
        this.address = address;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        setTotalPrice(getTotalPrice() + item.calculatePrice());
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
        this.deliveryMan.finishCurrentOrder();
        this.client.updateScore();
    }

    public boolean hasAsignedDeliveryMan() {
        return (this.deliveryMan != null);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
        deliveryMan.setFree(false);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isDelivered() {
        return delivered;
    }

}