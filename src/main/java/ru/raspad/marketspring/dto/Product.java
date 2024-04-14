package ru.raspad.marketspring.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;
    @Column( name = "title")
    private String title;
    @Column( name = "price")
    private Integer price;


}