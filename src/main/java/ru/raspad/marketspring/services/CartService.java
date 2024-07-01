package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.MarketSpringApplication;
import ru.raspad.marketspring.repositories.Cart;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    public void addToCart(String title) {
        cart.addToCart(title);
    }
}
