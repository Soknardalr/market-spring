package ru.raspad.marketspring.repositories;

import ru.raspad.marketspring.dto.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    List<Product> findAll();

    Product findByTitle(String title);

    void save(Product product);

    void update(Long id, String title);

    void deleteById(Long id);
}
