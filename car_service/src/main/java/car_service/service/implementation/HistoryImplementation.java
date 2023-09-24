package car_service.service.implementation;

import car_service.data.entity.*;
import car_service.data.repository.HistoryRepository;
import car_service.service.AutoServiceService;
import car_service.service.CustomerService;
import car_service.service.EmployeeService;
import car_service.service.HistoryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class HistoryImplementation implements HistoryService {
    private final HistoryRepository historyRepository;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final AutoServiceService autoService;

    public HistoryImplementation(HistoryRepository historyRepository, CustomerService customerService, EmployeeService employeeService, AutoServiceService autoService) {
        this.historyRepository = historyRepository;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.autoService = autoService;
    }

    public HistoryRepository getHistoryRepository() {
        return historyRepository;
    }

    @Override
    public List<History> getHistory() {
        return historyRepository.findAll();
    }

    @Override
    public History getHistory(long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid history id: " + id));
    }

    @Override
    public History createHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History updateHistory(History history, long id) {
        history.setIdHistory(id);

        return historyRepository.save(history);
    }

    @Override
    public void deleteHistory(long id) {
        historyRepository.deleteById(id);
    }

    @Override
    public List<History> findByIsPaidOrderByDateOfRepairDesc(boolean isPaid) {
        return historyRepository.findByIsPaidOrderByDateOfRepairDesc(isPaid);
    }

    @Override
    public List<History> findByIdHistoryBetweenAndIsPaidFalse(long firstId, long secondId) {
        return historyRepository.findByIdHistoryBetweenAndIsPaidFalse(firstId, secondId);
    }

    @Override
    public BigDecimal findFinalPriceByBrand(long idHistory) {
        History history = historyRepository.getById(idHistory);
        Car car = history.getCar();
        Brand brand = car.getBrand();
        List<TypeOfService> typeOfServices = history.getTypeOfServices();
        BigDecimal finalPrice = BigDecimal.valueOf(0);
        List<TypeOfServicePriceOvercharge> typeOfServicePriceOvercharges = new ArrayList<>();
        for (TypeOfService typeOfService1 : typeOfServices) {
            typeOfServicePriceOvercharges.addAll(typeOfService1.getTypeOfServicePriceOvercharges());

        }
        for (TypeOfServicePriceOvercharge typeOfServicePriceOvercharge1 : typeOfServicePriceOvercharges) {
            for (TypeOfService typeOfService1 : typeOfServices) {
                if (brand.equals(typeOfServicePriceOvercharge1.getBrand())) {
                    finalPrice = typeOfServicePriceOvercharge1.getBrandOvercharge().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(typeOfService1.getPrice()));
                    finalPrice = finalPrice.add(BigDecimal.valueOf(typeOfService1.getPrice()));
                }
            }
        }

        return finalPrice;
    }

    @Override
    public List<History> findByDateOfRepairNotLike(LocalDate dateOfRepair) {
        return historyRepository.findByDateOfRepairNotLike(dateOfRepair);
    }

    @Override
    public List<History> getHistoriesByCustomer(long id) {
        Customer customer = customerService.getCustomer(id);
        List<Car> cars = customer.getCars();
        List<History> histories = new ArrayList<>();

        for (Car car : cars) {
            histories.addAll(car.getHistories());
        }

        return histories;
    }

    @Override
    public List<History> getHistoriesByEmployee(long id) {
        Employee employee = employeeService.getEmployee(id);

        return employee.getHistories();
    }

    @Override
    public Set<History> getHistoriesByAutoService(long id) {
        AutoService autoService1 = autoService.getAutoService(id);
        List<Employee> employees = autoService1.getEmployees();
        Set<History> histories = new LinkedHashSet<>();

        for (Employee employee : employees) {
            histories.addAll(employee.getHistories());
        }

        return histories;
    }

}