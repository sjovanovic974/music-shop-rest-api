package sasa.jovanovic.musicshop.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sasa.jovanovic.musicshop.dto.Purchase;
import sasa.jovanovic.musicshop.dto.PurchaseResponse;
import sasa.jovanovic.musicshop.services.CheckoutService;

@RestController
@RequestMapping("checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);

    }
}
