package car_service.service.implementation;

import car_service.data.entity.*;
import car_service.data.repository.CarRepository;
import car_service.service.AutoServiceService;
import car_service.service.CarService;
import car_service.service.CustomerService;
import car_service.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImplementation implements CarService {

    private final CarRepository carRepository;
    private final EmployeeService employeeService;
    private final AutoServiceService autoService;

    private final CustomerService customerService;

    public CarServiceImplementation(CarRepository autoServiceRepository, EmployeeService employeeService, AutoServiceService autoService, CustomerService customerService) {
        this.carRepository = autoServiceRepository;
        this.employeeService = employeeService;
        this.autoService = autoService;
        this.customerService = customerService;
    }

    public CarRepository getAutoServiceRepository() {
        return carRepository;
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCars(long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car id: " + id));
    }

    @Override
    public Car create(Car autoService) {
        return carRepository.save(autoService);
    }

    @Override
    public Car updateCar(Car car, long id) {
        car.setId(id);

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findAllByRegistrationNumberStartsWith(String registrationNumber) {
        return carRepository.findAllByRegistrationNumberStartsWith(registrationNumber);
    }

    @Override
    public List<Car> getCarsByCustomer(long id) {
        Customer customer = customerService.getCustomer(id);
        List<Car> cars = customer.getCars();

        return cars;
    }

    @Override
    public List<Car> getCarsByEmployee(long id) {
        Employee employee = employeeService.getEmployee(id);
        List<History> histories = employee.getHistories();
        List<Car> cars = new ArrayList<>();

        for (History history : histories) {
            cars.add(history.getCar());
        }

        return cars;
    }

    @Override
    public Set<Car> getCarsByAutoService(long id) {
        AutoService autoService1 = autoService.getAutoService(id);
        List<Employee> employees = autoService1.getEmployees();
        Set<Car> cars = new LinkedHashSet<>();

        for (Employee employee : employees) {
            cars.addAll(getCarsByEmployee(employee.getId()));
        }

        return cars;
    }

}