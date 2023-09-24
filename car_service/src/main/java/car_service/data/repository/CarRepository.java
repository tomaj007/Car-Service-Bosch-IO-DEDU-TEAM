package car_service.data.repository;

import car_service.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByRegistrationNumberStartsWith(String registrationNumber);
}