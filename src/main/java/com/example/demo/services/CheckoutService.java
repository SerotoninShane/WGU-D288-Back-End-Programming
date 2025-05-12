package com.example.demo.services;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase); // Method to place an order
    void checkout(Purchase purchase); // Method to handle the checkout process
}
