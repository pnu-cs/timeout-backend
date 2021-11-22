package com.pnu.cs.timeout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(name = "photo", columnDefinition="bytea",  nullable = false)
    private byte[] photo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "use_for_description", nullable = false)
    private String useForDescription;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year_of_issue", nullable = false)
    private String yearOfIssue;

    @Column(name = "housing_material", nullable = false)
    private String housingMaterial;

    @Column(name = "strap_material", nullable = false)
    private String strapMaterial;

    @Column(name = "dial_color", nullable = false)
    private String dialColor;

    @Column(name = "strap_color", nullable = false)
    private String strapColor;

    @Column(name = "housing_thickness", nullable = false)
    private String housingThickness;

    @Column(name = "strap_length", nullable = false)
    private String strapLength;

    @Column(name = "clock_work", nullable = false)
    private String clockWork;

    @Column(name = "water_resistance", nullable = false)
    private String waterResistance;

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;
}
