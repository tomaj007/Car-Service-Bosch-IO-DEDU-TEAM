package car_service.controllers.api;

import car_service.data.entity.Employee;
import car_service.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {

    private final EmployeeService employeeService;

    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployee() {
        return employeeService.getEmployee();
    }

    @RequestMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping(value = "/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }

    //Dani
    @GetMapping(value = "/name/{name}/address/{address}")
    public List<Employee> findAllByNameAndAddress(@PathVariable("name") String name, @PathVariable("address") String address) {
        return employeeService.findAllByNameAndAddress(name, address);
    }

}