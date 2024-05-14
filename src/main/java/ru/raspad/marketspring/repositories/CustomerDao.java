package ru.raspad.marketspring.repositories;

import ru.raspad.marketspring.dto.Customer;

import java.util.List;

public interface CustomerDao {
    Customer findById(Long id);
    List<Customer> findAll();

    Customer findByName(String name);

    void save(Customer customer);

    void update(Long id, String name);

    void deleteById(Long id);
}
