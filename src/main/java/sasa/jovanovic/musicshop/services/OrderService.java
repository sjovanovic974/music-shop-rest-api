package sasa.jovanovic.musicshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sasa.jovanovic.musicshop.models.Order;

public interface OrderService {

    Page<Order> findByCustomerEmail(String email, Pageable pageable);
}
