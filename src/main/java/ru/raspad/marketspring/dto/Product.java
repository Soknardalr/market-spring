package ru.raspad.marketspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Data
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

    @ManyToMany(fetch = FetchType.EAGER) // todo: ???
    @JsonIgnore
    @JoinTable(
            name = "customers_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn (name = "customer_id")
    )
    private List<Customer> customers;


}
