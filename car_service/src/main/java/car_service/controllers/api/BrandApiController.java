package car_service.controllers.api;

import car_service.data.entity.Brand;
import car_service.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandApiController {

    private final BrandService brandService;

    public BrandApiController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getBrand() {
        return brandService.getBrand();
    }

    @RequestMapping("/{id}")
    public Brand getBrand(@PathVariable("id") long id) {
        return brandService.getBrand(id);
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }

    @PutMapping(value = "/{id}")
    public Brand updateBrand(@RequestBody Brand brand, @PathVariable("id") long id) {
        return brandService.updateBrand(brand, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBrand(@PathVariable long id) {
        brandService.deleteBrand(id);
    }

    //Toma
    @GetMapping("/name/{name}")
    public List<Brand> findAllByNameContaining(@PathVariable("name") String name) {
        return brandService.findAllByNameContaining(name);
    }

}