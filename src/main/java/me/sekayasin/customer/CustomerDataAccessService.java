package me.sekayasin.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessService implements CustomerDAO {

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
}
