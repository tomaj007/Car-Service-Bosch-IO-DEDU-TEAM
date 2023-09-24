package car_service.controllers.view;

import car_service.data.entity.History;
import car_service.data.entity.User;
import car_service.service.CarService;
import car_service.service.EmployeeService;
import car_service.service.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/historyView")
public class HistoryViewController {
    private final HistoryService historyService;
    private final CarService carService;
    private final EmployeeService employeeService;

    public HistoryViewController(HistoryService historyService, CarService carService, EmployeeService employeeService) {
        this.historyService = historyService;
        this.carService = carService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getHistories(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<History> histories = new ArrayList<>();
        Set<History> histories1 = new LinkedHashSet<>();

        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CUSTOMER"))) {
            histories = historyService.getHistoriesByCustomer(user.getId());

        } else if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("EMPLOYEE"))) {
            long autoServiceId = employeeService.getEmployee(user.getId()).getAutoService().getId();
            histories1 = historyService.getHistoriesByAutoService(autoServiceId);
            histories = (new ArrayList<>(histories1));

        } else {
            histories = historyService.getHistory();
        }

        histories = historyService.getHistory();
        model.addAttribute("histories", histories);
        return "/history/history";
    }

    @GetMapping("/create-history")
    public String showCreateHistoryForm(Model model) {
        model.addAttribute("history", new History());
        model.addAttribute("cars", carService.getCars());
        return "/history/create-history";
    }

    @PostMapping("/create")
    public String createHistory(@ModelAttribute History history) {
        historyService.createHistory(history);
        return "redirect:/historyView";
    }

    @GetMapping("/edit/{id}")
    public String showEditTypeOfServiceForm(Model model, @PathVariable Long id) {
        model.addAttribute("history", historyService.getHistory(id));
        model.addAttribute("cars", carService.getCars());
        return "/history/edit-history";
    }

    @PostMapping("/update/{id}")
    public String updateTypeOfService(Model model, @PathVariable Long id, History history) {
        historyService.updateHistory(history, id);
        return "redirect:/historyView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        historyService.deleteHistory(id);
        return "redirect:/historyView";
    }

    @GetMapping("/employeeHistory/{idEmployee}")
    public String getHistoriesByEmployee(Model model, @PathVariable("idEmployee") long idEmployee) {
        List<History> histories = historyService.getHistoriesByEmployee(idEmployee);
        model.addAttribute("histories", histories);
        return "/history/history";
    }

    @GetMapping("/autoServiceHistory/{autoServiceId}")
    public String getHistoriesByAutoService(Model model, @PathVariable("autoServiceId") long autoServiceId) {
        Set<History> histories = historyService.getHistoriesByAutoService(autoServiceId);
        model.addAttribute("histories", histories);
        return "/history/history";
    }

}