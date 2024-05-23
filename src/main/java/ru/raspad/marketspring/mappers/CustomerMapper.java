package ru.raspad.marketspring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.raspad.marketspring.dto.CustomerDto;
import ru.raspad.marketspring.entity.CustomerDao;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDao toDao(CustomerDto customer);
    @Mapping(source = "products", target = "productDtos")
    CustomerDto toDto(CustomerDao customer);

}
