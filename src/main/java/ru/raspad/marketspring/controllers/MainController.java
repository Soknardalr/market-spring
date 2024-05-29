package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.entity.ProductDao;
import ru.raspad.marketspring.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ProductService service;
    @GetMapping("/products")
    public List<ProductDto> getAllStudents(){
        return service.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable Long id){
        return service.getProduct(id);
    }
    @GetMapping("/product/change_price")
    public void changeScore(@RequestParam Long productId, @RequestParam Integer delta){
        service.changePrice(productId, delta);
    }

    @PostMapping("/product/add")
    public void postClient(@RequestBody ProductDto productDto){
        service.addProduct(productDto);
    }

    @GetMapping("/product/delete/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
