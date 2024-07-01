package ru.raspad.marketspring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.raspad.marketspring.dto.CustomerDto;
import ru.raspad.marketspring.entity.User;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    User toDao(CustomerDto customer);
    @Mapping(source = "products", target = "productDtos")
    CustomerDto toDto(User customer);

}
