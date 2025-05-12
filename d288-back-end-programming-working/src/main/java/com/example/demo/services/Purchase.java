package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public class Purchase {
    @Valid
    @NotNull(message = "Cart must not be null")
    private Cart cart;

    @Valid
    @NotNull(message = "Cart items must not be null")
    private Set<CartItem> cartItems;

    @Valid
    @NotNull(message = "Customer must not be null")
    private Customer customer;

    // Default constructor
    public Purchase() {}

    // Constructor with parameters
    public Purchase(Cart cart, Set<CartItem> cartItems, Customer customer) {
        this.cart = cart;
        this.cartItems = cartItems;
        this.customer = customer;
    }

    // Getters and Setters
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
