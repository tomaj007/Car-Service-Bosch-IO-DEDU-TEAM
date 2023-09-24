package car_service.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Car {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String registrationNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfProduction;

    @OneToMany(mappedBy = "car")
    @JsonIgnoreProperties("car")
    private List<History> histories;

    public List<History> getHistories() {
        return histories;
    }

    @ManyToOne
    @JsonIgnore
    private Brand brand;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    public Car(long id, String model, String registrationNumber, LocalDate yearOfProduction, List<History> histories, Brand brand, Customer customer) {
        this.id = id;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.yearOfProduction = yearOfProduction;
        this.histories = histories;
        this.brand = brand;
        this.customer = customer;
    }

    public Car() {}

    public Customer getCustomer() {
        return customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(LocalDate yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}