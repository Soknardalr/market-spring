package ru.raspad.marketspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.raspad.marketspring.entity.ProductDao;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDao, Long>, JpaSpecificationExecutor<ProductDao> {

    boolean existsProductDaoByTitle(String title);
    boolean existsProductDaoById (Long id);
    @Query("select p from ProductDao p where p.price > :min and p.price < :max")
    List<ProductDao> getProductsByPriceBetween(Integer min, Integer max);
}
