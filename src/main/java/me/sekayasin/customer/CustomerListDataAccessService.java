package me.sekayasin.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO {

    private static final List<Customer> customers;

//    Fake Database
    static {
        customers = new ArrayList<>();

        Customer pypy = new Customer(
                1,
                "Pypy",
                "pypy@gmail.com",
                23
        );

        customers.add(pypy);

        Customer badru = new Customer(
                2,
                "Badru",
                "badru@gmail.com",
                15
        );
        customers.add(badru);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> findCustomerById(Integer customerId) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customers.stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        // customers.removeIf(customer -> customer.getId().equals(customerId));

        customers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public boolean existsCustomerById(Integer customerId) {
        return customers.stream()
                .anyMatch(customer -> customer.getId().equals(customerId));
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
