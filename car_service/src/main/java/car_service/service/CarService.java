package car_service.service;

import car_service.data.entity.Car;

import java.util.List;
import java.util.Set;

public interface CarService {
    List<Car> getCars();

    Car getCars(long id);

    Car create(Car autoService);

    Car updateCar(Car car, long id);

    void deleteCar(long id);

    //Ivo
    List<Car> findAllByRegistrationNumberStartsWith(String registrationNumber);

    List<Car> getCarsByCustomer(long id);

    List<Car> getCarsByEmployee(long id);

    Set<Car> getCarsByAutoService(long id);
}