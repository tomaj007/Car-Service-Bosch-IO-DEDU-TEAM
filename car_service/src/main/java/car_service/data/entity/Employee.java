package car_service.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "idEmployees")
public class Employee extends User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    private String name;
    private String address;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnoreProperties("employees")
    private List<TypeOfService> typeOfServices;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnoreProperties("employees")
    private List<History> histories;

    @ManyToOne
    private AutoService autoService;

    public Employee(long id, String username, String password, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, Set<Role> authorities, String name, String address, AutoService autoService) {
        super(id, username, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, authorities);
        this.name = name;
        this.address = address;
        this.autoService = autoService;
    }

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Employee() {
    }

    public List<TypeOfService> getTypeOfServices() {
        return typeOfServices;
    }

    public List<History> getHistories() {
        return histories;
    }

    public AutoService getAutoService() {
        return autoService;
    }

//    public long getIdEmployees() {
//        return id;
//    }
//
//    public void setIdEmployees(long id) {
//        this.id = id;
//    }

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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", autoService=" + autoService +
                "} " + super.toString();
    }

}