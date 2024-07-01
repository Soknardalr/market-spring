package ru.raspad.marketspring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {
    private final Map<String, Integer> cartMem;
    public Cart() {
        cartMem = new HashMap<>();
    }
    public void addToCart(String title){
        cartMem.put(title, cartMem.getOrDefault(title, 0) + 1);
        System.out.println(cartMem);
    }
}
