package ru.raspad.marketspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.raspad.marketspring.entity.ProductDao;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDao, Long> {
    // change to Optional?
    public List<ProductDao> getProductDaosByPriceIsLessThan(Integer max);
    public List<ProductDao> getProductDaosByPriceIsGreaterThan(Integer min);
    @Query("select p from ProductDao p where p.price > :min and p.price < :max")
    public List<ProductDao> getProductsByPriceBetween(Integer min, Integer max);
}
