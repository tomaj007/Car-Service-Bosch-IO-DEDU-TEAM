package car_service.controllers.api;

import car_service.data.entity.TypeOfService;
import car_service.data.entity.TypeOfServicePriceOvercharge;
import car_service.service.TypeOfServicePriceOverchargeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeOfServicePriceOvercharge")
public class TypeOfServicePriceOverchargeApiController {

    private final TypeOfServicePriceOverchargeService typeOfServicePriceOverchargeService;

    public TypeOfServicePriceOverchargeApiController(TypeOfServicePriceOverchargeService typeOfServicePriceOverchargeService) {
        this.typeOfServicePriceOverchargeService = typeOfServicePriceOverchargeService;
    }

    @GetMapping
    public List<TypeOfServicePriceOvercharge> getTypeOfServicePriceOvercharge() {
        return typeOfServicePriceOverchargeService.getTypeOfServicePriceOvercharge();
    }

    @RequestMapping("/{id}")
    public TypeOfServicePriceOvercharge getTypeOfServicePriceOvercharge(@PathVariable("id") int id) {
        return typeOfServicePriceOverchargeService.getTypeOfServicePriceOvercharge(id);
    }

    @PostMapping
    public TypeOfServicePriceOvercharge createTypeOfServicePriceOvercharge(@RequestBody TypeOfServicePriceOvercharge typeOfServicePriceOvercharge) {
        return typeOfServicePriceOverchargeService.createTypeOfServicePriceOvercharge(typeOfServicePriceOvercharge);
    }

    @PutMapping(value = "/{id}")
    public TypeOfServicePriceOvercharge updateTypeOfServicePriceOvercharge(@RequestBody TypeOfServicePriceOvercharge typeOfServicePriceOvercharge, @PathVariable("id") long id) {
        return typeOfServicePriceOverchargeService.updateTypeOfServicePriceOvercharge(typeOfServicePriceOvercharge, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTypeOfServicePriceOvercharge(@PathVariable long id) {
        typeOfServicePriceOverchargeService.deleteTypeOfServicePriceOvercharge(id);
    }

    @GetMapping(value = "/search-type-of-service/{typeOfServiceId}")
    public TypeOfService findDistinctByName(@PathVariable("typeOfServiceId") long id) {
        return typeOfServicePriceOverchargeService.findByTypeOfService(id);
    }

}