package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @ManyToOne//(FK)
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean enabled;
}
