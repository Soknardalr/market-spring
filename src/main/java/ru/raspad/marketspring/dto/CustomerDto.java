package ru.raspad.marketspring.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private List<ProductDto> productDtos;
}
