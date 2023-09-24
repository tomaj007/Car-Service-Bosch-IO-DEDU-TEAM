package car_service.controllers.api;

import car_service.data.entity.Car;
import car_service.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarApiController {

    private final CarService carService;

    public CarApiController(CarService carService) {
        this.carService = carService;
    }

    public CarService getCarServiceService() {
        return carService;
    }

    @GetMapping
    public List<Car> getAutoService() {
        return carService.getCars();
    }

    @RequestMapping("/{id}")
    public Car getAutoService(@PathVariable("id") int id) {
        return carService.getCars(id);
    }

    @PostMapping
    public Car createAutoService(@RequestBody Car car) {
        return carService.create(car);
    }

    @PutMapping(value = "/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable("id") long id) {
        return carService.updateCar(car, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

    //Ivo
    @GetMapping("/registrationNumber/{registrationNumber}")
    public List<Car> findAllByRegistrationNumberStartsWith(@PathVariable("registrationNumber") String registrationNumber) {
        return carService.findAllByRegistrationNumberStartsWith(registrationNumber);
    }

}