package me.sekayasin.customer;

import me.sekayasin.exception.RequestValidationException;
import me.sekayasin.exception.DuplicateResourceException;
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

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        // Check if email exist
        if (customerDAO.existsCustomerWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException("email already taken");
        }

        // add new customer
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDAO.addCustomer(customer);
    }

    public void deleteCustomer(Integer customerId) {
        // check if customer exists
        if (!customerDAO.existsCustomerById(customerId)) {
            throw new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId));
        }

        // delete customer
        customerDAO.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest) {
        // get customer or throw exception
        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if (customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())) {
            customer.setName(customerUpdateRequest.name());
            changes = true;
        }
        if (customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())) {
            // check if email exists
            if (customerDAO.existsCustomerWithEmail(customerUpdateRequest.email())) {
                throw new DuplicateResourceException("email already taken");
            }
            customer.setEmail(customerUpdateRequest.email());
            changes = true;
        }
        if (customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(customer.getAge())) {
            customer.setAge(customerUpdateRequest.age());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        customerDAO.updateCustomer(customer);
    }

}
