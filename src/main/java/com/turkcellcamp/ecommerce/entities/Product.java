package com.turkcellcamp.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.turkcellcamp.ecommerce.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    private Set<Category> categoryList = new HashSet<>();
}
