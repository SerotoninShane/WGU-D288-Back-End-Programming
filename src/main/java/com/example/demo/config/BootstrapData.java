package com.example.demo.config;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapData {

    @Bean
    CommandLineRunner initData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        return args -> {

            // Assuming the divisions already exist in the DB, fetch them
            Division division1 = divisionRepository.findById(1L).orElse(null);
            Division division2 = divisionRepository.findById(2L).orElse(null);
            Division division3 = divisionRepository.findById(3L).orElse(null);
            Division division4 = divisionRepository.findById(4L).orElse(null);
            Division division5 = divisionRepository.findById(5L).orElse(null);

            // Add 5 unique customers
            Customer customer1 = new Customer("Shane", "Bogue", "123 Maple St", "12345", "555-1234", division1);
            Customer customer2 = new Customer("Jane", "Smith", "456 Oak St", "67890", "555-5678", division2);
            Customer customer3 = new Customer("Michael", "Johnson", "789 Pine St", "11223", "555-9876", division3);
            Customer customer4 = new Customer("Emily", "Davis", "321 Birch St", "44556", "555-6543", division4);
            Customer customer5 = new Customer("David", "Brown", "654 Cedar St", "77889", "555-4321", division5);

            // Save them to the repository
            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);
        };
    }
}
