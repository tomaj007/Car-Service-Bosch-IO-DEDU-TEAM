package car_service.service;

import car_service.data.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    List<Customer> getCustomer();

    Customer getCustomer(long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer, long id);

    void deleteCustomer(long id);

    //Toma
    Double findCustomerBill(long id);

    //Kiril
    List<Customer> findAllByIdGreaterThanEqual(long id);

    //Kiril
    Double findCustomerBillBeforeDate(long id, LocalDate dateOfRepair);
}