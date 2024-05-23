package ru.raspad.marketspring.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private List<CustomerDto> customerDtos;
}
