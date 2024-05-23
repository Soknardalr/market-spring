package ru.raspad.marketspring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.entity.ProductDao;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDao toDao(ProductDto product);
    @Mapping(source = "customers", target = "customerDtos")
    ProductDto toDto(ProductDao product);
}
