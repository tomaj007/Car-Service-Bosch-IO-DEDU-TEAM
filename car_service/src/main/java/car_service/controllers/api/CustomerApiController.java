package car_service.controllers.api;

import car_service.data.entity.Customer;
import car_service.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    @RequestMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") int id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping(value = "/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") long id) {
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
    }

    //Toma
    @GetMapping("/customer-bill/id/{id}")
    public Double findCustomerBill(@PathVariable("id") long id) {
        return customerService.findCustomerBill(id);
    }

    //Kiril
    @GetMapping("/id/{id}")
    public List<Customer> findAllByIdGreaterThanEqual(@PathVariable("id") long id) {
        return customerService.findAllByIdGreaterThanEqual(id);
    }

    //Kiril
    @GetMapping("/customer-bill-before-date/id/{id}/dateOfRepair/{dateOfRepair}")
    public Double findCustomerBillBeforeDate(@PathVariable("id") long id, @PathVariable("dateOfRepair") LocalDate dateOfRepair) {
        return customerService.findCustomerBillBeforeDate(id, dateOfRepair);
    }

}