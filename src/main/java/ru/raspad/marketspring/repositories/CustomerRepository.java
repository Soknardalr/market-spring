package ru.raspad.marketspring.repositories;

import ru.raspad.marketspring.entity.CustomerDao;

import java.util.List;

public interface CustomerRepository {
    CustomerDao findById(Long id);
    List<CustomerDao> findAll();

    CustomerDao findByName(String name);

    void save(CustomerDao customerDao);

    void update(Long id, String name);

    void deleteById(Long id);
}
