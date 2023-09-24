package car_service.service;

import car_service.data.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee();

    Employee getEmployee(long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee, long id);

    void deleteEmployee(long id);

    //Dani
    List<Employee> findAllByNameAndAddress(String name, String address);
}