package me.sekayasin;

import me.sekayasin.customer.Customer;
import me.sekayasin.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {

            Customer pypy = new Customer(
                    "Pypy",
                    "pypy@gmail.com",
                    23
            );

            Customer badru = new Customer(
                    "Badru",
                    "badru@gmail.com",
                    15
            );

            List<Customer> customers = List.of(pypy, badru);
            customerRepository.saveAll(customers);
        };
    }
}

