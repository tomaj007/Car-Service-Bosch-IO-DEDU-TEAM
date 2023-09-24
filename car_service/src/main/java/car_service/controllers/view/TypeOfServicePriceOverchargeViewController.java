package car_service.controllers.view;

import car_service.data.entity.TypeOfServicePriceOvercharge;
import car_service.service.BrandService;
import car_service.service.TypeOfServicePriceOverchargeService;
import car_service.service.TypeOfServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/typeOfServicePriceOverchargeView")
public class TypeOfServicePriceOverchargeViewController {

    private final BrandService brandService;
    private final TypeOfServiceService typeOfServiceService;
    private final TypeOfServicePriceOverchargeService typeOfServicePriceOverchargeService;

    public TypeOfServicePriceOverchargeViewController(BrandService brandService, TypeOfServiceService typeOfServiceService, TypeOfServicePriceOverchargeService typeOfServicePriceOverchargeService) {
        this.brandService = brandService;
        this.typeOfServiceService = typeOfServiceService;
        this.typeOfServicePriceOverchargeService = typeOfServicePriceOverchargeService;
    }

    @GetMapping
    public String getTypeOfServicePriceOvercharge(Model model) {
        final List<TypeOfServicePriceOvercharge> typeOfServicePriceOvercharges = typeOfServicePriceOverchargeService.getTypeOfServicePriceOvercharge();
        model.addAttribute("typeOfServicePriceOvercharges", typeOfServicePriceOvercharges);
        return "/typeOfServicePriceOvercharge/typeOfServicePriceOvercharge";
    }

    @GetMapping("/edit/{id}")
    public String showEditTypeOfServicePriceOvercharge(Model model, @PathVariable Long id) {
        model.addAttribute("typeOfServicePriceOvercharge", typeOfServicePriceOverchargeService.getTypeOfServicePriceOvercharge(id));
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("typeOfServices", typeOfServiceService.getTypeOfService());
        return "/typeOfServicePriceOvercharge/edit-type-of-service-price-overcharge";
    }

    @GetMapping("/create-type-of-service-price-overcharge")
    public String showCreateTypeOfServicePriceOvercharge(Model model) {
        model.addAttribute("typeOfServicePriceOvercharge", new TypeOfServicePriceOvercharge());
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("typeOfServices", typeOfServiceService.getTypeOfService());
        return "/typeOfServicePriceOvercharge/create-type-of-service-price-overcharge";
    }

    @PostMapping("/create")
    public String createTypeOfServicePriceOvercharge(Model model, TypeOfServicePriceOvercharge typeOfServicePriceOvercharge) {
        typeOfServicePriceOverchargeService.createTypeOfServicePriceOvercharge(typeOfServicePriceOvercharge);
        return "redirect:/typeOfServicePriceOverchargeView";
    }

    @PostMapping("/update/{id}")
    public String updateTypeOfServicePriceOvercharge(Model model, @PathVariable Long id, TypeOfServicePriceOvercharge typeOfServicePriceOvercharge) {
        typeOfServicePriceOverchargeService.updateTypeOfServicePriceOvercharge(typeOfServicePriceOvercharge, id);
        return "redirect:/typeOfServicePriceOverchargeView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        typeOfServicePriceOverchargeService.deleteTypeOfServicePriceOvercharge(id);
        return "redirect:/typeOfServicePriceOverchargeView";
    }

}