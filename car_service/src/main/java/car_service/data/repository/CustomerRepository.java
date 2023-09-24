package car_service.data.repository;

import car_service.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByIdGreaterThanEqual(long id);
}