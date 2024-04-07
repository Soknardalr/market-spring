package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.Product;
import ru.raspad.marketspring.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product getProduct(Long id){
        return repository.findById(id);
    }

    public List<Product> getAllProducts(){
        return repository.getAllProducts();
    }
}
