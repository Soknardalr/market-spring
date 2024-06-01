package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ProductService service;

    @GetMapping(value = "/products/filter", params = {"min_price", "max_price"})
    public List<ProductDto> getProductsByPriceBetween(@RequestParam(name = "min_price") Integer min,
                                                      @RequestParam(name = "max_price") Integer max) {
        return service.getProductsByPriceBetween(min, max);
    }

    @GetMapping(value = "/products/filter", params = "max_price")
    public List<ProductDto> getProductsMaxPrice(@RequestParam(name = "max_price") Integer max) {
        return service.getProductsMaxPrice(max);
    }

    @GetMapping(value = "/products/filter", params = "min_price")
    public List<ProductDto> getProductsMinPrice(@RequestParam(name = "min_price") Integer minPrice) {
        return service.getProductsMinPrice(minPrice);
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping("/product/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        service.changePrice(productId, delta);
    }

    @PostMapping("/product/add")
    public void postProduct(@RequestBody ProductDto productDto) {
        service.addProduct(productDto);
    }

    @GetMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }
}
