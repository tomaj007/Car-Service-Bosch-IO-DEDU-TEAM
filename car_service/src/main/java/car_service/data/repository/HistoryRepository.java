package car_service.data.repository;

import car_service.data.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByIsPaidOrderByDateOfRepairDesc(boolean isPaid);

    List<History> findByIdHistoryBetweenAndIsPaidFalse(long firstId, long secondId);

    List<History> findByDateOfRepairNotLike(LocalDate dateOfRepair);
}