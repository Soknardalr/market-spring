package ru.raspad.marketspring.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = "singleton")
public class Cart {
    private final Map<Long, Integer> cartMem = new HashMap<>();

    public void addToCart(Long id){
        cartMem.put(id, cartMem.getOrDefault(id, 1));
        System.out.println(cartMem);
    }
}
