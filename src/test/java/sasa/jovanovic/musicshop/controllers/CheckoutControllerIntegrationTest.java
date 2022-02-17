package sasa.jovanovic.musicshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.dto.Purchase;
import sasa.jovanovic.musicshop.dto.PurchaseResponse;
import sasa.jovanovic.musicshop.models.Address;
import sasa.jovanovic.musicshop.models.Customer;
import sasa.jovanovic.musicshop.models.Order;
import sasa.jovanovic.musicshop.models.OrderItem;
import sasa.jovanovic.musicshop.services.CheckoutService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CheckoutController.class)
class CheckoutControllerIntegrationTest {

    @MockBean
    private CheckoutService service;

    @Autowired
    private MockMvc mockMvc;

    private Purchase purchase = new Purchase();

    @BeforeEach
    void setUp() {

        Customer customer = new Customer();
        Address shippingAddress = new Address();
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();

        purchase.setCustomer(customer);
        purchase.setShippingAddress(shippingAddress);
        purchase.setBillingAddress(shippingAddress);
        purchase.setOrder(order);
        purchase.setOrderItems(orderItems);
    }

    @Test
    @DisplayName("Should place order")
    void placeOrder() throws Exception {

        String orderTrackingNumber = "123e4567-e89b-12d3-a456-556642440000";

        when(service.placeOrder(any(Purchase.class)))
                .thenReturn(new PurchaseResponse(orderTrackingNumber));

        mockMvc.perform(post("/checkout/purchase")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(purchase)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.orderTrackingNumber").value(orderTrackingNumber));

        verify(service).placeOrder(any(Purchase.class));

    }
}