package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.MarketSpringApplication;
import ru.raspad.marketspring.repositories.Cart;

@Service
public class CartService {

    Cart cart = ctx.getBean(Cart.class);
    public void addToCart(Long id) {
        cart.addToCart(id);
    }
}
