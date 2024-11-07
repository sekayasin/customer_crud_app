package me.sekayasin.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> findAllCustomers();
    Optional<Customer> findCustomerById(Integer customerId);
    void addCustomer(Customer customer);
    boolean existsCustomerWithEmail(String email);
    void deleteCustomerById(Integer customerId);
    boolean existsCustomerById(Integer customerId);
    void updateCustomer(Customer customer);
}
