package ru.raspad.marketspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;
    @Column( name = "title")
    private String title;
    @Column( name = "price")
    private Integer price;
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<User> customers;

}
