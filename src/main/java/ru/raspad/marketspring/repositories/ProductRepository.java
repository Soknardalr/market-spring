package ru.raspad.marketspring.repositories;

import ru.raspad.marketspring.entity.ProductDao;

import java.util.List;

public interface ProductRepository {
    ProductDao findById(Long id);
    List<ProductDao> findAll();

    ProductDao findByTitle(String title);

    void save(ProductDao productDao);

    void update(Long id, String title);

    void deleteById(Long id);
}
