package car_service.service;

import java.time.LocalDate;
import java.util.List;

import car_service.data.entity.IdCard;

public interface IdCardService {
    List<IdCard> getIdCard();

    IdCard getIdCard(long id);

    IdCard createIdCard(IdCard idCard);

    IdCard updateIdCard(IdCard idCard, long id);

    void deleteIdCard(long id);

    //All Dani
    List<IdCard> findByBirthdateBetween(LocalDate firstDate, LocalDate secondDate);

    List<IdCard> findByBirthdateNotLike(LocalDate birthdate);

    List<IdCard> findByIdLessThanOrderByLink(long id);

    List<IdCard> findAllByOrderByBirthdateAscLinkDesc();

    IdCard getIdCardByCustomer(long id);
}