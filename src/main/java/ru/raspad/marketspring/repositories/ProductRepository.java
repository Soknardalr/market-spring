package ru.raspad.marketspring.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.dto.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "gashgash", 100),
                new Product(2L, "quincy", 100),
                new Product(3L, "cursed", 100)
        ));
    }

    public Product findById(Long id){
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("student not found"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Long id, String name){
        products.add(new Product(id, name, 0));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteById(Long id) {
        products.removeIf(c -> c.getId().equals(id));
    }
}
