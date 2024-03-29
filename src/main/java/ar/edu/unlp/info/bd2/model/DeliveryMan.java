package ar.edu.unlp.info.bd2.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javax.persistence.CascadeType.ALL;

@Entity
@DiscriminatorValue("2")
public class DeliveryMan extends User {

    @Column(name = "number_of_success_orders", nullable = false, columnDefinition = "int default 0")
    private int numberOfSuccessOrders;

    @Column(name = "date_of_admission", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateOfAdmission;

    @Column(name = "free", nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean free;

    public DeliveryMan() {}

    public DeliveryMan(String name, String username, String password, String email, Date dateOfBirth) {
        super(name, username, password, email, dateOfBirth);
        this.numberOfSuccessOrders = 0;
        this.dateOfAdmission = new Date();
        this.free = true;
        this.setOrders(new ArrayList<>());
    }

    public void finishCurrentOrder() {
        this.free = true;
        this.numberOfSuccessOrders++;
        this.setScore(this.getScore() + 1);
    }

    public int getNumberOfSuccessOrders() {
        return numberOfSuccessOrders;
    }

    public void setNumberOfSuccessOrders(int numberOfSuccessOrders) {
        this.numberOfSuccessOrders = numberOfSuccessOrders;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

}
