package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.CustomerDto;
import ru.raspad.marketspring.mappers.CustomerMapper;
import ru.raspad.marketspring.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerDto getCustomer(Long id){
        return CustomerMapper.INSTANCE.toDto(customerRepository.findById(id).orElseThrow());
    }
    public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAll().stream().map(CustomerMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
    public void addCustomer(Long id, String name){
    }
    public void addCustomer(CustomerDto customerDto) {
        customerRepository.save(CustomerMapper.INSTANCE.toDao(customerDto));
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}


