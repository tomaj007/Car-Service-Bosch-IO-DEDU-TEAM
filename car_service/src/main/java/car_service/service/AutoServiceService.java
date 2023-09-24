package car_service.service;

import car_service.data.entity.AutoService;
import car_service.data.entity.Customer;
import car_service.data.entity.History;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AutoServiceService {
    List<AutoService> getAutoService();

    AutoService getAutoService(long id);

    AutoService createAutoService(AutoService autoService);

    AutoService updateAutoService(AutoService autoService, long id);

    void deleteAutoService(long id);

    //Ivo
    List<AutoService> findDistinctByName(String name);

    //Ivo
    List<AutoService> findAllByNameAndAddressEndingWithOrderByAddressDesc(String name, String address);

    //Ivo
    List<AutoService> findAllByNameOrAddress(String name, String address);

    //Ivo
    List<AutoService> findAllByAddressEndingWithAndIdGreaterThan(String address, long id);

    //Ivo
    Set<Customer> findAllCustomersByAutoService(long id);

    //Ivo
    List<History> findEmployeeHistoriesByAutoService(long id, long employeeId);

    //Ivo
    List<History> findAllEmployeesHistoryByAutoService(long id);

    //Ivo
    Double findAllEmployeesIncomeByAutoService(long id);

    //Ivo
    Double findAllEmployeesIncomeByAutoService(long id, long brandId, LocalDate dateOfRepairAfter, LocalDate dateOfRepairBefore);

    //Toma
    Double sumOfProfit(long id, LocalDate dateOfRepair, LocalDate dateOfRepair2, long idTypeOfService);

    //Kiril
    Double findAllEmployeesIncomeByBrand(long id, long brandId);

    //Dani
    Double findAllIncomeFromAutoService(long id);

    //Dani
    Double findAllEmployeesIncomeByAutoService(long id, LocalDate afterDate);
}