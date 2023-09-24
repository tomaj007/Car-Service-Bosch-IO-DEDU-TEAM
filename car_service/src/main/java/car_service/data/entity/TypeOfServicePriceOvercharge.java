package car_service.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class TypeOfServicePriceOvercharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal brandOvercharge;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    @JsonIgnore
    private TypeOfService typeOfService;

    public TypeOfServicePriceOvercharge(long id, BigDecimal brandOvercharge) {
        this.id = id;
        this.brandOvercharge = brandOvercharge;
    }

    public TypeOfServicePriceOvercharge(long id, BigDecimal brandOvercharge, Brand brand, TypeOfService typeOfService) {
        this.id = id;
        this.brandOvercharge = brandOvercharge;
        this.brand = brand;
        this.typeOfService = typeOfService;
    }

    public TypeOfServicePriceOvercharge() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBrandOvercharge() {
        return brandOvercharge;
    }

    public void setBrandOvercharge(BigDecimal brandOvercharge) {
        this.brandOvercharge = brandOvercharge;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public TypeOfService getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(TypeOfService typeOfService) {
        this.typeOfService = typeOfService;
    }

}