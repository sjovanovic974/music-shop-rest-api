package sasa.jovanovic.musicshop.dto;

import lombok.Getter;
import lombok.Setter;
import sasa.jovanovic.musicshop.models.Address;
import sasa.jovanovic.musicshop.models.Customer;
import sasa.jovanovic.musicshop.models.Order;
import sasa.jovanovic.musicshop.models.OrderItem;

import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
