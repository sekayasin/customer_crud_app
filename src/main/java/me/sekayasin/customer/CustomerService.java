package me.sekayasin.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAllCustomers();
    }

    public Customer getCustomer(Integer customerId) {
        return customerDAO.findCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id [%s] not found".formatted(customerId)));
    }


}
