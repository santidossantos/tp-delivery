package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "score", nullable = false)
    private float score; // De 1 a 5 estrellas

    @Column(name = "commentary", length = 100)
    private String commentary;

    @OneToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    public Qualification() {}

    public Qualification(String commentary, Order order) {
        this.commentary = commentary;
        this.order = order;
    }

    public Qualification(float score, String commentary, Order order) {
        this(commentary, order);
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}