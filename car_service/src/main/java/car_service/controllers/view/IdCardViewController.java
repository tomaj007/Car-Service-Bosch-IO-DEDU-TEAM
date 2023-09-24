package car_service.controllers.view;

import car_service.data.entity.IdCard;
import car_service.data.entity.User;
import car_service.service.CustomerService;
import car_service.service.IdCardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/idCardView")
public class IdCardViewController {

    private final IdCardService idCardService;
    private final CustomerService customerService;

    public IdCardViewController(IdCardService idCardService, CustomerService customerService) {
        this.idCardService = idCardService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getIdCard(Model model) {
        final List<IdCard> idCards = idCardService.getIdCard();
        model.addAttribute("idCards", idCards);
        return "/idCard/idCard";
    }

    @GetMapping("/edit/{id}")
    public String showEditIdCard(Model model, @PathVariable Long id) {
        model.addAttribute("idCard", idCardService.getIdCard(id));
        model.addAttribute("customers", customerService.getCustomer());
        return "/idCard/edit-idCard";
    }

    @PostMapping("/update/{id}")
    public String updateIdCard(Model model, @PathVariable Long id, IdCard idCard) {
        idCardService.updateIdCard(idCard, id);
        return "redirect:/idCardView";
    }

    @GetMapping("/create-idCard")
    public String showCreateIdCardForm(Model model) {
        model.addAttribute("idCard", new IdCard());
        model.addAttribute("customers", customerService.getCustomer());
        return "/idCard/create-idCard";
    }

    @PostMapping("/create")
    public String createIdCard(@ModelAttribute IdCard idCard) {
        idCardService.createIdCard(idCard);
        return "redirect:/idCardView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        idCardService.deleteIdCard(id);
        return "redirect:/idCardView";
    }

    @GetMapping("/customer")
    public String getIdCardByCustomer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<IdCard> idCards = new ArrayList<>();
        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CUSTOMER"))) {
            idCards.add(idCardService.getIdCardByCustomer(user.getId()));

        } else {
            idCards = idCardService.getIdCard();
        }

        model.addAttribute("idCards", idCards);
        return "/idCard/idCard";
    }

}