package sasa.jovanovic.musicshop.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.Order;
import sasa.jovanovic.musicshop.repos.OrderRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> findByCustomerEmail(String email, Pageable pageable) {
        Page<Order> orders = orderRepository.findByCustomerEmail(email,pageable);

        if (orders.getContent().size() == 0) {
            log.error(">>> No orders found!");
            throw new NotFoundException("No orders found!");
        }

        return orders;
    }
}
