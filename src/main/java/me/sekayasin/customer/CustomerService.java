package me.sekayasin.customer;

import me.sekayasin.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAllCustomers();
    }

    public Customer getCustomer(Integer customerId) {
        return customerDAO.findCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId)));
    }

}
