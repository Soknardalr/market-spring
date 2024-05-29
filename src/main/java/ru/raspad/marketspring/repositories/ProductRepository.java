package ru.raspad.marketspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raspad.marketspring.entity.ProductDao;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDao, Long> {
}
