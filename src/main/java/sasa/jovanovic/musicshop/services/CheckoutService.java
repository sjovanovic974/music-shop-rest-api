package sasa.jovanovic.musicshop.services;

import sasa.jovanovic.musicshop.dto.Purchase;
import sasa.jovanovic.musicshop.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
