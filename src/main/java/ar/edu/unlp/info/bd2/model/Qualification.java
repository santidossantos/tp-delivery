package ar.edu.unlp.info.bd2.model;


public class Qualification {

    private float score; //De 1 a 5 estrellas

    private String commentary;

    private Order order;


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

    public Long getId() {
        return null;
    }
}
