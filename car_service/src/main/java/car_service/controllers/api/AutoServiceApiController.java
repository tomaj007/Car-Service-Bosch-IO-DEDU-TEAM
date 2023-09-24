package car_service.controllers.api;

import car_service.data.entity.*;
import car_service.service.AutoServiceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auto-service")
public class AutoServiceApiController {

    private final AutoServiceService autoServiceService;

    public AutoServiceApiController(AutoServiceService autoServiceService) {
        this.autoServiceService = autoServiceService;
    }

    public AutoServiceService getAutoServiceService() {
        return autoServiceService;
    }

    @GetMapping
    public List<AutoService> getAutoService() {
        return autoServiceService.getAutoService();
    }

    @RequestMapping("/{id}")
    public AutoService getAutoService(@PathVariable("id") int id) {
        return autoServiceService.getAutoService(id);
    }

    @PostMapping
    public AutoService createAutoService(@RequestBody AutoService autoService) {
        return autoServiceService.createAutoService(autoService);
    }

    @PutMapping(value = "/{id}")
    public AutoService updateAutoService(@RequestBody AutoService autoService, @PathVariable("id") long id) {
        return autoServiceService.updateAutoService(autoService, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAutoService(@PathVariable("id") long id) {
        autoServiceService.deleteAutoService(id);
    }

    //IVO
    @GetMapping(value = "/search-distinct-name/{name}")
    public List<AutoService> findDistinctByName(@PathVariable("name") String name) {
        return autoServiceService.findDistinctByName(name);
    }

    //IVO
    @GetMapping(value = "/search-name-and-address/name/{name}/address/{address}")
    public List<AutoService> findAllByNameAndAddressEndingWithOrderByAddressDesc(@PathVariable("name") String name, @PathVariable("address") String address) {
        return autoServiceService.findAllByNameAndAddressEndingWithOrderByAddressDesc(name, address);
    }

    //IVO
    @GetMapping(value = "/search-or/name/{name}/address/{address}")
    public List<AutoService> findAllByNameOrAddress(@PathVariable("name") String name, @PathVariable("address") String address) {
        return autoServiceService.findAllByNameOrAddress(name, address);
    }

    //IVO
    @GetMapping(value = "/search-address-and-id/address/{address}/id/{id}")
    public List<AutoService> findAllByAddressEndingWithAndIdGreaterThan(@PathVariable("address") String address, @PathVariable("id") long id) {
        return autoServiceService.findAllByAddressEndingWithAndIdGreaterThan(address, id);
    }

    //IVO
    @GetMapping(value = "/search-all-employees/{id}")
    public List<Employee> findAllEmployeesByAutoService(@PathVariable("id") long id) {
        AutoService autoService = autoServiceService.getAutoService(id);

        return autoService.getEmployees();
    }

    //IVO
    @GetMapping(value = "/search-all-customers/{id}")
    public Set<Customer> findAllCustomersByAutoService(@PathVariable("id") long id) {
        return autoServiceService.findAllCustomersByAutoService(id);
    }

    //IVO
    @GetMapping(value = "/search-all-type-of-service/{id}")
    public List<TypeOfService> findAllTypeOfServiceByAutoService(@PathVariable("id") long id) {
        AutoService autoService = autoServiceService.getAutoService(id);

        return autoService.getTypeOfServices();
    }

    //IVO
    @GetMapping(value = "/search-all-brand/{id}")
    public List<Brand> findAllBrandByAutoService(@PathVariable("id") long id) {
        AutoService autoService = autoServiceService.getAutoService(id);

        return autoService.getBrand();
    }

    //IVO
    @GetMapping(value = "/search-all-employees-history/auto-service-id/{id}/employee-id/{employeeId}")
    public List<History> findEmployeeHistoriesByAutoService(@PathVariable("id") long id, @PathVariable("employeeId") long employeeId) {
        return autoServiceService.findEmployeeHistoriesByAutoService(id, employeeId);
    }

    //IVO
    @GetMapping(value = "/search-all-employees-history/auto-service-id/{id}")
    public List<History> findAllEmployeesHistoryByAutoService(@PathVariable("id") long id) {
        return autoServiceService.findAllEmployeesHistoryByAutoService(id);
    }

    //IVO
    @GetMapping(value = "/get-auto-service-income1/auto-service/{id}")
    public Double findAllEmployeesIncomeByAutoService(@PathVariable("id") long id) {
        return autoServiceService.findAllEmployeesIncomeByAutoService(id);
    }

    //IVO
    @GetMapping(value = "/get-auto-service-income-by-brand/auto-service/{id}/brand/{brandId}/date-of-repair-after/{dateOfRepairAfter}/date-of-repair-before/{dateOfRepairBefore}")
    public Double findAllEmployeesIncomeByAutoService(@PathVariable("id") long id, @PathVariable("brandId") long brandId, @PathVariable("dateOfRepairAfter") LocalDate dateOfRepairAfter, @PathVariable("dateOfRepairBefore") LocalDate dateOfRepairBefore) {
        return autoServiceService.findAllEmployeesIncomeByAutoService(id, brandId, dateOfRepairAfter, dateOfRepairBefore);
    }

    //TOMA
    @GetMapping(value = "/sumOfProfit/auto-service-id/{id}/typeOfService/{idTypeOfService}/firstDate/{dateOfRepair}/secondDate/{dateOfRepair2}")
    public Double sumOfProfit(@PathVariable("id") long id, @PathVariable("dateOfRepair") LocalDate dateOfRepair, @PathVariable("dateOfRepair2") LocalDate dateOfRepair2, @PathVariable("idTypeOfService") long idTypeOfService) {
        return autoServiceService.sumOfProfit(id, dateOfRepair, dateOfRepair2, idTypeOfService);
    }

    //KIRIL
    @GetMapping(value = "/get-auto-service-income-by-brand/auto-service/{id}/brand/{brandId}")
    public Double findAllEmployeesIncomeByBrand(@PathVariable("id") long id, @PathVariable("brandId") long brandId) {
        return autoServiceService.findAllEmployeesIncomeByBrand(id, brandId);
    }

    //DANI
    @GetMapping(value = "/get-auto-service-income/auto-service/{id}")
    public Double findAllIncomeFromAutoService(@PathVariable("id") long id) {
        return autoServiceService.findAllIncomeFromAutoService(id);
    }

    //DANI
    @GetMapping(value = "/get-auto-service-income/auto-service/{id}/after/{afterDate}")
    public Double findAllEmployeesIncomeByAutoService(@PathVariable("id") long id, @PathVariable("afterDate") LocalDate afterDate) {
        return autoServiceService.findAllEmployeesIncomeByAutoService(id, afterDate);
    }

}