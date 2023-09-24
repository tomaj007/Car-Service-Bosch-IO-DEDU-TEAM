package car_service.data.repository;

import car_service.data.entity.AutoService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoServiceRepository extends JpaRepository<AutoService, Long> {
    List<AutoService> findDistinctByName(String name);

    List<AutoService> findAllByNameAndAddressEndingWithOrderByAddressDesc(String name, String address);

    List<AutoService> findAllByNameOrAddress(String name, String address);

    List<AutoService> findAllByAddressEndingWithAndIdGreaterThan(String address, long id);
}