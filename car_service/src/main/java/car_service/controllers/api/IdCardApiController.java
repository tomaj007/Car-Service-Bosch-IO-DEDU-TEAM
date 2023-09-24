package car_service.controllers.api;

import car_service.data.entity.IdCard;
import car_service.service.IdCardService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/idCard")
public class IdCardApiController {

    private final IdCardService idCardService;

    public IdCardApiController(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    @GetMapping
    public List<IdCard> getIdCard() {
        return idCardService.getIdCard();
    }

    @RequestMapping("/{id}")
    public IdCard getIdCard(@PathVariable("id") int id) {
        return idCardService.getIdCard(id);
    }

    @PostMapping
    public IdCard createIdCard(@RequestBody IdCard idCard) {
        return idCardService.createIdCard(idCard);
    }

    @PutMapping(value = "/{id}")
    public IdCard updateIdCard(@RequestBody IdCard idCard, @PathVariable("id") long id) {
        return idCardService.updateIdCard(idCard, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteIdCard(@PathVariable long id) {
        idCardService.deleteIdCard(id);
    }

    //All Dani
    @GetMapping(value = "/find-by-birth-date-between/firstDate/{firstDate}/secondDate/{secondDate}")
    public List<IdCard> findByBirthDateBetween(@PathVariable("firstDate") LocalDate firstDate, @PathVariable("secondDate") LocalDate secondDate) {
        return idCardService.findByBirthdateBetween(firstDate, secondDate);
    }

    @GetMapping(value = "/find-by-birthdate-not-like/birthdate/{birthdate}")
    public List<IdCard> findByBirthdateNotLike(@PathVariable("birthdate") LocalDate birthdate) {
        return idCardService.findByBirthdateNotLike(birthdate);
    }

    @GetMapping(value = "/find-by-id-less-than-order-by-link/id/{id}")
    public List<IdCard> findByIdLessThanOrderByLink(@PathVariable("id") long id) {
        return idCardService.findByIdLessThanOrderByLink(id);
    }

    @GetMapping(value = "/find-all-order-by-birthdate-and-link-desc")
    public List<IdCard> findAllByOrderByBirthdateAscLinkDesc() {
        return idCardService.findAllByOrderByBirthdateAscLinkDesc();
    }

}