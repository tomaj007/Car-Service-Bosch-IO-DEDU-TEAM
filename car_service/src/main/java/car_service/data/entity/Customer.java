package car_service.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "idCustomers")
public class Customer extends User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    private String name;

    @OneToOne
    @JsonIgnore
    private IdCard idCard;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<Car> cars;

    public Customer(long id, String username, String password, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, Set<Role> authorities, String name, IdCard idCard) {
        super(id, username, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, authorities);
        this.name = name;
        this.idCard = idCard;
    }

    public Customer() {
    }

//    public void setIdCustomers(long id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

//    public long getIdCustomers() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + "} ";
    }

}