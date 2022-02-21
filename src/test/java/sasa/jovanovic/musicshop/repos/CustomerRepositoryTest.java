package sasa.jovanovic.musicshop.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sasa.jovanovic.musicshop.models.Customer;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("DEV")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    @DisplayName("Should find customer by email")
    void findByEmail() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("email@gmail.com");
        customer.setOrders(new HashSet<>());

        repository.save(customer);

        Customer customerFromDB = repository.findByEmail(customer.getEmail());

        assertThat(customerFromDB).isNotNull();
        assertThat(customerFromDB.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    @DisplayName("Should not find customer by email")
    void findNotByEmail() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("email@gmail.com");
        customer.setOrders(new HashSet<>());


        Customer customerFromDB = repository.findByEmail(customer.getEmail());

        assertThat(customerFromDB).isNull();
    }
}