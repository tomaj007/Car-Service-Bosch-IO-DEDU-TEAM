package car_service.data.repository;

import car_service.data.entity.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IdCardRepository extends JpaRepository<IdCard, Long> {
    List<IdCard> findByBirthdateBetween(LocalDate firstDate, LocalDate secondDate);

    List<IdCard> findByBirthdateNotLike(LocalDate birthdate);

    List<IdCard> findByIdLessThanOrderByLink(long id);

    List<IdCard> findAllByOrderByBirthdateAscLinkDesc();
}

