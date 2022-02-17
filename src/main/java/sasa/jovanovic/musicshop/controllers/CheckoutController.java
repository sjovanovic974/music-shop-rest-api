package sasa.jovanovic.musicshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sasa.jovanovic.musicshop.dto.Purchase;
import sasa.jovanovic.musicshop.dto.PurchaseResponse;
import sasa.jovanovic.musicshop.services.CheckoutService;

import javax.validation.Valid;

@RestController
@RequestMapping("checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PurchaseResponse placeOrder(@Valid @RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);

    }
}
