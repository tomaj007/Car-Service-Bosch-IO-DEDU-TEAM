package car_service.data.repository;

import car_service.data.entity.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeOfServiceRepository extends JpaRepository<TypeOfService, Long> {
    List<TypeOfService>findByPriceBetween(double smallPrice,double bigPrice);

    List<TypeOfService>findByDescriptionContaining(String description);

    List<TypeOfService>findByDescription(String description);

    List<TypeOfService>findByIdTypeOfServiceLessThanOrderByType(long idTypeOfService);
}
