package car_service.controllers.view;

import car_service.data.entity.Employee;
import car_service.data.entity.Employee;
import car_service.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employeeView")
public class EmployeeViewController {

    private final EmployeeService employeeService;

    public EmployeeViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployee(Model model) {
        final List<Employee> employees = employeeService.getEmployee();
        model.addAttribute("employees", employees);
        return "/employee/employee";
    }

    @GetMapping("/edit/{id}")
    public String showEditEmployee(Model model, @PathVariable Long id) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "/employee/edit-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(Model model, @PathVariable Long id, Employee employee) {
        employeeService.updateEmployee(employee, id);
        return "redirect:/employeeView";
    }
    @GetMapping("/create-employee")
    public String showCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "/employee/create-employee";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employeeView";
    }
    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employeeView";
    }
}