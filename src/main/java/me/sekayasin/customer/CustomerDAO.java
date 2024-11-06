package me.sekayasin.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> findAllCustomers();
    Optional<Customer> findCustomerById(Integer customerId);
}
