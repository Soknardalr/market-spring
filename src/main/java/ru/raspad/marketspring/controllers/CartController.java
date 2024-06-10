package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raspad.marketspring.services.CartService;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

   private final CartService service;
    @PostMapping("/{id}")
    public void addToCart(@PathVariable Long id){
        service.addToCart(id);
    }
}
