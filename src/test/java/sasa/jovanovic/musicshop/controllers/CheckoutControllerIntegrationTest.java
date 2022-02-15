package sasa.jovanovic.musicshop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.services.CheckoutService;

@WebMvcTest(CheckoutController.class)
@AutoConfigureMockMvc
class CheckoutControllerIntegrationTest {

    @MockBean
    private CheckoutService service;

    @Autowired
    CheckoutController controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void placeOrder() {
    }
}