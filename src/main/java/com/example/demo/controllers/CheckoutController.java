package com.example.demo.controllers;

import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseResponse;
import com.example.demo.services.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "http://localhost:4200")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    // Constructor injection for the CheckoutService
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // POST mapping for handling purchases
    @PostMapping("/purchase")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody Purchase purchase, BindingResult result) {
        if (result.hasErrors()) {
            // Return validation errors
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            // Delegate the business logic to the CheckoutService
            PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

            // Return the order tracking number and HTTP status CREATED
            return ResponseEntity.status(HttpStatus.CREATED).body(purchaseResponse);
        } catch (Exception e) {
            logger.error("Error placing order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error placing order. Please try again.");
        }
    }
}
