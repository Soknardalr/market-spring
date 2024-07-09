package ru.raspad.marketspring.validators;

import org.springframework.stereotype.Component;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.exceptions.ValidateException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto product){
        List<String> errors = new ArrayList<>();
        if (product.getPrice()<1){
            errors.add("Price cannot be lower than 1");
        }
        if (product.getTitle().isBlank()){
            errors.add("Title cannot be empty");
        }
        if (!errors.isEmpty()) throw new ValidateException(errors);
    }
}
