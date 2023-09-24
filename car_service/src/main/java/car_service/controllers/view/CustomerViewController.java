package car_service.controllers.view;

import car_service.data.entity.Customer;
import car_service.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customerView")
public class CustomerViewController {

    private final CustomerService customerService;

    public CustomerViewController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomer(Model model) {
        final List<Customer> customers = customerService.getCustomer();
        model.addAttribute("customers", customers);
        return "/customer/customer";
    }

    @GetMapping("/edit-create")
    public String showCreateCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/create-customer";
    }

    @PostMapping("/create")
    public String createCustomer(Model model, Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customerView";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomer(Model model, @PathVariable Long id) {
        model.addAttribute("customer", customerService.getCustomer(id));
        return "/customer/edit-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(Model model, @PathVariable Long id, Customer customer) {
        customerService.updateCustomer(customer, id);
        return "redirect:/customerView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "redirect:/customerView";
    }

}