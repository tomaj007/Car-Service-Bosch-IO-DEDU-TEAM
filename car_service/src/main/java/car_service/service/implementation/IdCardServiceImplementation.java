package car_service.service.implementation;

import car_service.data.entity.Customer;
import car_service.data.entity.IdCard;
import car_service.data.repository.IdCardRepository;
import car_service.service.CustomerService;
import car_service.service.IdCardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IdCardServiceImplementation implements IdCardService {

    private final IdCardRepository idCardRepository;
    private final CustomerService customerService;

    public IdCardServiceImplementation(IdCardRepository idCardRepository, CustomerService customerService) {
        this.idCardRepository = idCardRepository;
        this.customerService = customerService;
    }

    public IdCardRepository getIdCardRepository() {
        return idCardRepository;
    }

    @Override
    public List<IdCard> getIdCard() {
        return idCardRepository.findAll();
    }

    @Override
    public IdCard getIdCard(long id) {
        return idCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid IdCard id: " + id));
    }

    @Override
    public IdCard createIdCard(IdCard idCard) {
        return idCardRepository.save(idCard);
    }

    @Override
    public void deleteIdCard(long id) {
        idCardRepository.deleteById(id);
    }

    @Override
    public IdCard updateIdCard(IdCard idCard, long id) {
        idCard.setId(id);

        return idCardRepository.save(idCard);
    }

    @Override
    public List<IdCard> findByBirthdateBetween(LocalDate firstDate, LocalDate secondDate) {
        return idCardRepository.findByBirthdateBetween(firstDate, secondDate);
    }

    @Override
    public List<IdCard> findByBirthdateNotLike(LocalDate birthdate) {
        return idCardRepository.findByBirthdateNotLike(birthdate);
    }

    @Override
    public List<IdCard> findByIdLessThanOrderByLink(long id) {
        return idCardRepository.findByIdLessThanOrderByLink(id);
    }

    @Override
    public List<IdCard> findAllByOrderByBirthdateAscLinkDesc() {
        return idCardRepository.findAllByOrderByBirthdateAscLinkDesc();
    }

    @Override
    public IdCard getIdCardByCustomer(long id) {
        Customer customer = customerService.getCustomer(id);
        return customer.getIdCard();
    }

}