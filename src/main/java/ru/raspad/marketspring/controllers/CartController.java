package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/app/api/v1/cart")
public class CartController {
    public CartController(@Autowired CartService service) {
        this.service = service;
    }
    private final CartService service;

    @GetMapping
    public List<ProductDto> getAllFromCart(){
        return null;
    }
    @PostMapping("/{title}")
    public void addToCart(@PathVariable String title){
        service.addToCart(title);
    }
}
