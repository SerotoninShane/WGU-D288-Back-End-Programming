package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "First name is required")
    @Column(name = "customer_first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "customer_last_name")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Postal code is required")
    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id") // This should match the database column
    private Division division; // This should remain as 'division'

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Cart> carts = new HashSet<>();

    public void add(Cart cart) {

        if (cart != null) {

            if (carts == null) {
                carts = new HashSet<Cart>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }
    }

    public Customer() {}

    public Customer(String firstName, String lastName, String address, String postal_code, String phone, Division division) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.division = division;
    }
}
