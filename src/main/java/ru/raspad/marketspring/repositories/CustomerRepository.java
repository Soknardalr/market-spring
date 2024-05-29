package ru.raspad.marketspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raspad.marketspring.entity.CustomerDao;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerDao, Long> {
}
