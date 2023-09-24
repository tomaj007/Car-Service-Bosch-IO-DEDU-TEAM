package car_service.service.implementation;

import car_service.data.entity.*;
import car_service.data.repository.CustomerRepository;
import car_service.service.CustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerImplementation implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, long id) {
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAllByIdGreaterThanEqual(long id) {
        return customerRepository.findAllByIdGreaterThanEqual(id);
    }

    //Toma
    @Override
    public Double findCustomerBill(long id) {
        double income = 0;

        Customer customer = getCustomer(id);

        List<Car> cars = customer.getCars();
        List<History> histories = new ArrayList<>();
        List<TypeOfService> typeOfServices = new ArrayList<>();

        for (Car car1 : cars) {
            histories.addAll(car1.getHistories());
        }

        for (History history1 : histories) {
            typeOfServices.addAll(history1.getTypeOfServices());
        }

        for (TypeOfService typeOfService1 : typeOfServices) {
            income += typeOfService1.getPrice();
        }

        return income;
    }

    //Kiril
    @Override
    public Double findCustomerBillBeforeDate(long id, LocalDate dateOfRepair) {
        double income = 0;

        Customer customer = getCustomer(id);

        List<Car> cars = customer.getCars();
        List<History> histories = new ArrayList<>();
        List<TypeOfService> typeOfServices = new ArrayList<>();

        for (Car car1 : cars) {
            histories.addAll(car1.getHistories());
        }

        for (History history1 : histories) {
            if (history1.getDateOfRepair().isBefore(dateOfRepair)) {
                typeOfServices.addAll(history1.getTypeOfServices());
            }
        }

        for (TypeOfService typeOfService1 : typeOfServices) {
            income += typeOfService1.getPrice();
        }

        return income;
    }

}