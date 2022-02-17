package sasa.jovanovic.musicshop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasa.jovanovic.musicshop.dto.Purchase;
import sasa.jovanovic.musicshop.dto.PurchaseResponse;
import sasa.jovanovic.musicshop.models.Customer;
import sasa.jovanovic.musicshop.models.Order;
import sasa.jovanovic.musicshop.models.OrderItem;
import sasa.jovanovic.musicshop.repos.CustomerRepository;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplUnitTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CheckoutServiceImpl service;

    private Purchase purchase;
    private Customer customer;

    @BeforeEach
    void setUp() {
        purchase = new Purchase();
        Order order = new Order();
        purchase.setOrder(order);

        OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(item1);
        orderItems.add(item2);

        purchase.setOrderItems(orderItems);

        customer = new Customer();
        purchase.setCustomer(customer);
        customer.add(order);
    }

    @Test
    @DisplayName("Should return a valid UUID")
    void placeOrder() {

        when(repository.save(any(Customer.class))).thenReturn(customer);

        PurchaseResponse response = service.placeOrder(purchase);

        assertThat(response.getOrderTrackingNumber()).isNotEmpty();
        assertThat(response.getOrderTrackingNumber())
                .matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        verify(repository, times(1)).save(any(Customer.class));
    }
}