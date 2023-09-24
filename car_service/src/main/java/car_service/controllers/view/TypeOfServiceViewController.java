package car_service.controllers.view;

import car_service.data.entity.*;
import car_service.service.AutoServiceService;
import car_service.service.EmployeeService;
import car_service.service.TypeOfServiceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/typeOfServiceView")
public class TypeOfServiceViewController {

    private final TypeOfServiceService typeOfServiceService;
    private final AutoServiceService autoServiceService;
    private final EmployeeService employeeService;

    public TypeOfServiceViewController(TypeOfServiceService typeOfServiceService, AutoServiceService autoServiceService, EmployeeService employeeService) {
        this.typeOfServiceService = typeOfServiceService;
        this.autoServiceService = autoServiceService;
        this.employeeService = employeeService;
    }

    @GetMapping("/edit-type-of-service")
    public String showCreateTypeOfService(Model model) {
        model.addAttribute("typeOfService", new TypeOfService());
        return "/typeOfService/create-type-of-service";
    }

    @PostMapping("/create")
    public String createTypeOfService(Model model, TypeOfService typeOfService) {
        typeOfServiceService.createTypeOfService(typeOfService);
        return "redirect:/typeOfServiceView";
    }

    @GetMapping("/edit/{id}")
    public String showEditTypeOfServiceForm(Model model, @PathVariable Long id) {
        model.addAttribute("typeOfService", typeOfServiceService.getTypeOfService(id));
        return "/typeOfService/edit-type-of-service";
    }

    @PostMapping("/update/{id}")
    public String updateTypeOfService(Model model, @PathVariable Long id, TypeOfService typeOfService) {
        typeOfServiceService.updateTypeOfService(typeOfService, id);
        return "redirect:/typeOfServiceView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        typeOfServiceService.deleteTypeOfService(id);
        return "redirect:/typeOfServiceView";
    }

    @GetMapping
    public String getTypeOfService(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<TypeOfService> typeOfServices = new ArrayList<>();

        if (user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("EMPLOYEE"))) {
            long autoServiceId = employeeService.getEmployee(user.getId()).getAutoService().getId();
            typeOfServices = autoServiceService.getAutoService(autoServiceId).getTypeOfServices();
        } else {
            typeOfServices = typeOfServiceService.getTypeOfService();
        }

        model.addAttribute("typeOfServices", typeOfServices);
        return "/typeOfService/typeOfService";
    }

}