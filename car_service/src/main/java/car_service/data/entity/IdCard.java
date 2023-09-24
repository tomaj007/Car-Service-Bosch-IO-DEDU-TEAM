package car_service.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class IdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String link;

    @OneToOne(mappedBy = "idCard")
    @JsonIgnore
    private Customer customer;

    public IdCard(long id, LocalDate birthdate, String link, Customer customer) {
        this.id = id;
        this.birthdate = birthdate;
        this.link = link;
        this.customer = customer;
    }

    public IdCard() {}

    public void setId(long id) {
        this.id = id;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getId() {
        return id;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "id=" + id +
                ", birthdate=" + birthdate +
                ", link='" + link + '}';
    }

}