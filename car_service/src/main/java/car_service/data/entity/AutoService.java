package car_service.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class AutoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "auto_service_brand",
            joinColumns = @JoinColumn(name = "auto_services_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id_brands"))
    private List<Brand> brand;

    @ManyToMany(mappedBy = "autoServices")
    @JsonIgnore
    private List<TypeOfService> typeOfServices;

    @OneToMany(mappedBy = "autoService")
    @JsonIgnore
    private List<Employee> employees;

    public AutoService(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public AutoService() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<TypeOfService> getTypeOfServices() {
        return typeOfServices;
    }

}