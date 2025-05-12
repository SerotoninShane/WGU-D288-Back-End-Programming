package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        try {
            // Validate the customer object
            Customer customer = purchase.getCustomer();
            if (customer == null) {
                throw new IllegalArgumentException("Customer cannot be null");
            }

            // Validate that cart items are present
            Set<CartItem> cartItems = purchase.getCartItems();
            if (cartItems == null || cartItems.isEmpty()) {
                throw new IllegalArgumentException("Cart cannot be empty");
            }

            // Retrieve the cart from the purchase DTO
            Cart cart = purchase.getCart();

            // Generate tracking number
            String orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);

            // Populate the cart with cart items
            cartItems.forEach(cart::add);

            // Associate customer with the cart
            cart.setCustomer(customer);

            // Set cart status as ordered
            cart.setStatus(StatusType.ordered);

            // Save the cart
            cartRepository.save(cart);

            // Associate and save the customer
            customer.add(cart);
            customerRepository.save(customer);

            return new PurchaseResponse(orderTrackingNumber);

        } catch (IllegalArgumentException e) {
            // Log the error message and rethrow exception if needed
            System.err.println("Error during checkout: " + e.getMessage());
            throw e; // Optionally rethrow, or handle accordingly
        }
    }

    private String generateOrderTrackingNumber() {
        // Generate a random UUID for tracking
        return UUID.randomUUID().toString();
    }

    @Override
    public void checkout(Purchase purchase) {
        // Implement checkout logic here if needed
    }
}
