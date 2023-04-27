package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javax.persistence.CascadeType.ALL;

@Entity
@DiscriminatorValue("1")
public class Client extends User {

    @Column(name = "date_of_register", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateOfRegister;

    @OneToMany(mappedBy = "client", cascade = ALL)
    private List<Address> addresses = new ArrayList<>();

    public Client() {}

    public Client(String name, String username, String password, String email, Date dateOfBirth) {
        super(name, username, password, email, dateOfBirth);
        this.dateOfRegister = new Date();
    }

    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void updateScore() {
        this.setScore(this.getScore() + 1);
    }

}
