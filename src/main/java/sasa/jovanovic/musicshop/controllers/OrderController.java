package sasa.jovanovic.musicshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasa.jovanovic.musicshop.models.Order;
import sasa.jovanovic.musicshop.services.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/search")
    public Page<Order> findOrdersByCustomerEmail(@RequestParam("email") String email, Pageable page) {
        return orderService.findByCustomerEmail(email, page);
    }
}
