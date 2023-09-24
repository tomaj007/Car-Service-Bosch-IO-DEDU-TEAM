package car_service.service.implementation;

import car_service.data.entity.*;
import car_service.data.repository.AutoServiceRepository;
import car_service.service.AutoServiceService;
import car_service.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AutoServiceServiceImplementation implements AutoServiceService {
    private final EmployeeService employeeService;
    private final AutoServiceRepository autoServiceRepository;

    public AutoServiceServiceImplementation(EmployeeService employeeService, AutoServiceRepository autoServiceRepository) {
        this.employeeService = employeeService;
        this.autoServiceRepository = autoServiceRepository;
    }

    public AutoServiceRepository getAutoServiceRepository() {
        return autoServiceRepository;
    }

    @Override
    public List<AutoService> getAutoService() {
        return autoServiceRepository.findAll();
    }

    @Override
    public AutoService getAutoService(long id) {
        return autoServiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid autoService id: " + id));
    }

    @Override
    public AutoService createAutoService(AutoService autoService) {
        return autoServiceRepository.save(autoService);
    }

    @Override
    public AutoService updateAutoService(AutoService autoService, long id) {
        autoService.setId(id);

        return autoServiceRepository.save(autoService);
    }

    @Override
    public void deleteAutoService(long id) {
        autoServiceRepository.deleteById(id);
    }

    @Override
    public List<AutoService> findDistinctByName(String name) {
        return autoServiceRepository.findDistinctByName(name);
    }

    @Override
    public List<AutoService> findAllByNameAndAddressEndingWithOrderByAddressDesc(String name, String address) {
        return autoServiceRepository.findAllByNameAndAddressEndingWithOrderByAddressDesc(name, address);
    }

    @Override
    public List<AutoService> findAllByNameOrAddress(String name, String address) {
        return autoServiceRepository.findAllByNameOrAddress(name, address);
    }

    @Override
    public List<AutoService> findAllByAddressEndingWithAndIdGreaterThan(String address, long id) {
        return autoServiceRepository.findAllByAddressEndingWithAndIdGreaterThan(address, id);
    }

    //Ivo
    @Override
    public Set<Customer> findAllCustomersByAutoService(long id) {
        AutoService autoService = getAutoService(id);


        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        Set<Customer> customers = new HashSet<>();

        for (Employee employee1 : employees) {
            histories.addAll(employee1.getHistories());
        }

        for (History history1 : histories) {
            cars.add(history1.getCar());
        }

        for (Car car1 : cars) {
            customers.add(car1.getCustomer());
        }

        return customers;
    }

    //Kiril
    @Override
    public Double findAllEmployeesIncomeByBrand(long id, long brandId) {
        double sum = 0;

        AutoService autoService = getAutoService(id);
        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();

        for (Employee employee1 : employees) {
            for (History history1 : employee1.getHistories()) {
                if (history1.getCar().getBrand().getIdBrands() == brandId) {
                    histories.add(history1);
                }
            }
        }

        for (History history1 : histories) {
            for (TypeOfService typeOfServices1 : history1.getTypeOfServices()) {
                sum += typeOfServices1.getPrice();
            }
        }

        return sum;
    }

    //Ivo
    @Override
    public List<History> findAllEmployeesHistoryByAutoService(long id) {
        AutoService autoService = getAutoService(id);

        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();

        for (Employee employee : employees) {
            histories.addAll(employee.getHistories());
        }

        return histories;
    }

    //Ivo
    @Override
    public List<History> findEmployeeHistoriesByAutoService(long id, long employeeId) {
        AutoService autoService = getAutoService(id);

        List<Employee> employees = autoService.getEmployees();
        Employee employee = employeeService.getEmployee(employeeId);
        List<History> histories = null;

        if (employees.contains(employee)) {
            histories = employee.getHistories();
        }

        return histories;
    }

    //Ivo
    @Override
    public Double findAllEmployeesIncomeByAutoService(long id) {
        double sum = 0;

        AutoService autoService = getAutoService(id);
        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();

        for (Employee employee : employees) {
            histories.addAll(employee.getHistories());
        }

        List<TypeOfService> typeOfService = new ArrayList<>();

        for (History history : histories) {
            typeOfService.addAll(history.getTypeOfServices());
        }

        for (TypeOfService typeOfService1 : typeOfService) {
            sum += typeOfService1.getPrice();
        }

        return sum;
    }

    //Ivo
    @Override
    public Double findAllEmployeesIncomeByAutoService(long id, long brandId, LocalDate dateOfRepairAfter, LocalDate dateOfRepairBefore) {
        double sum = 0;

        AutoService autoService = getAutoService(id);
        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();

        for (Employee employee : employees) {
            histories.addAll(employee.getHistories());
        }

        List<History> brandHistories = new ArrayList<>();

        for (History history : histories) {
            if (history.getCar().getBrand().getIdBrands() == brandId) {
                brandHistories.add(history);
            }
        }

        List<TypeOfService> typeOfService = new ArrayList<>();

        for (History history : brandHistories) {
            if (history.getDateOfRepair().isAfter(dateOfRepairAfter) && history.getDateOfRepair().isBefore(dateOfRepairBefore)) {
                typeOfService.addAll(history.getTypeOfServices());
            }
        }

        for (TypeOfService typeOfService1 : typeOfService) {
            sum += typeOfService1.getPrice();
        }

        return sum;
    }

    //Toma
    @Override
    public Double sumOfProfit(long id, LocalDate dateOfRepair, LocalDate dateOfRepair2, long idTypeOfService) {
        AutoService autoService = getAutoService(id);

        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();
        List<TypeOfService> typeOfService = new ArrayList<>();

        double income = 0;

        for (Employee employee1 : employees) {
            histories.addAll(employee1.getHistories());
        }

        for (History history1 : histories) {
            if (history1.isPaid()) {
                if (history1.getDateOfRepair().isAfter(dateOfRepair) && history1.getDateOfRepair().isBefore(dateOfRepair2)) {
                    typeOfService.addAll(history1.getTypeOfServices());
                }
            }
        }

        for (TypeOfService typeOfService1 : typeOfService) {
            if (typeOfService1.getIdTypeOfService() == idTypeOfService) {
                income += typeOfService1.getPrice();
            }
        }

        return income;
    }

    //Dani
    @Override
    public Double findAllIncomeFromAutoService(long id) {
        double income = 0;

        AutoService autoService = getAutoService(id);

        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();

        for (Employee employee : employees) {
            histories.addAll(employee.getHistories());
        }

        List<TypeOfService> typeOfService = new ArrayList<>();

        for (History history : histories) {
            if (history.isPaid()) {
                typeOfService.addAll(history.getTypeOfServices());
            }
        }

        for (TypeOfService typeOfService1 : typeOfService) {
            income += typeOfService1.getPrice();
        }

        return income;
    }

    //Dani
    @Override
    public Double findAllEmployeesIncomeByAutoService(long id, LocalDate afterDate) {
        double income = 0;

        AutoService autoService = getAutoService(id);
        List<Employee> employees = autoService.getEmployees();
        List<History> histories = new ArrayList<>();

        for (Employee employee : employees) {
            histories.addAll(employee.getHistories());
        }

        List<TypeOfService> typeOfService = new ArrayList<>();

        for (History history : histories) {
            if (history.getDateOfRepair().isAfter(afterDate)) {
                typeOfService.addAll(history.getTypeOfServices());
            }
        }

        for (TypeOfService typeOfService1 : typeOfService) {
            income += typeOfService1.getPrice();
        }

        return income;
    }

}