package car_service.controllers.api;

import car_service.data.entity.TypeOfService;
import car_service.service.TypeOfServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeOfService")
public class TypeOfServiceApiController {

    private final TypeOfServiceService typeOfServiceService;

    public TypeOfServiceApiController(TypeOfServiceService typeOfServiceService) {
        this.typeOfServiceService = typeOfServiceService;
    }

    @GetMapping
    public List<TypeOfService> getTypeOfService() {
        return typeOfServiceService.getTypeOfService();
    }

    @RequestMapping("/{id}")
    public TypeOfService getTypeOfService(@PathVariable("id") int id) {
        return typeOfServiceService.getTypeOfService(id);
    }

    @PostMapping
    public TypeOfService createTypeOfService(@RequestBody TypeOfService typeOfService) {
        return typeOfServiceService.createTypeOfService(typeOfService);
    }

    @PutMapping(value = "/{id}")
    public TypeOfService updateTypeOfService(@RequestBody TypeOfService typeOfService, @PathVariable("id") long id) {
        return typeOfServiceService.updateTypeOfService(typeOfService, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTypeOfService(@PathVariable long id) {
        typeOfServiceService.deleteTypeOfService(id);
    }

    //All Kiril
    @GetMapping("/price/{smallPrice}/{bigPrice}")
    public List<TypeOfService> findByPriceBetween(@PathVariable("price") double smallPrice, double bigPrice) {
        return typeOfServiceService.findByPriceBetween(smallPrice, bigPrice);
    }

    @GetMapping("/description/{description}")
    public List<TypeOfService> findByDescriptionContaining(@PathVariable("description") String description) {
        return typeOfServiceService.findByDescriptionContaining(description);
    }

    @GetMapping("/description-not-in/{description}")
    public List<TypeOfService> findByDescriptionNotIn(@PathVariable("description") String description) {
        return typeOfServiceService.findByDescription(description);
    }

    @GetMapping("/id-less-than/{idTypeOfService}")
    public List<TypeOfService> findByIdLessThan(@PathVariable("idTypeOfService") long idTypeOfService) {
        return typeOfServiceService.findByIdLessThanOrderByType(idTypeOfService);
    }

}