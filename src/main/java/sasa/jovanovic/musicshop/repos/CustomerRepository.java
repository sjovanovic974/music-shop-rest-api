package sasa.jovanovic.musicshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sasa.jovanovic.musicshop.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
