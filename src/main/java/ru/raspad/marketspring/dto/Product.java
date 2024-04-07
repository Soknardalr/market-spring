package ru.raspad.marketspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private int cost;
}
