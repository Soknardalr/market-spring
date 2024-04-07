package ru.raspad.marketspring.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.dto.Product;
import ru.raspad.marketspring.dto.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk", 50),
                new Product(2L, "Bread", 30),
                new Product(3L, "Rofls", 200),
                new Product(4L, "Vodarik", 200),
                new Product(5L, "Pips", 100)
        ));
    }

    public Product findById(Long id){
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("product not found"));
    }

    public List<Product> getAllProducts() {
        return products;
    }
}


