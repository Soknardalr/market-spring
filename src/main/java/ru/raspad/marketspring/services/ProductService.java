package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.Product;
import ru.raspad.marketspring.repositories.ProductDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public Product getProduct(Long id){
        return productDao.findById(id);
    }

    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    public void addProduct(Long id, String title){

    }

    public void addProduct(Product product) {
        productDao.save(product);
    }

    public void changePrice(Long id, Integer delta) {
        Product product = productDao.findById(id);
        product.setPrice(product.getPrice() + delta);
    }

    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}
