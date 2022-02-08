package sasa.jovanovic.musicshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sasa.jovanovic.musicshop.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
