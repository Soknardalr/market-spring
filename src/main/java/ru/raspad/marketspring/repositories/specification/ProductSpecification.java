package ru.raspad.marketspring.repositories.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ru.raspad.marketspring.entity.ProductDao;

public class ProductSpecification {

    public static Specification<ProductDao> priceGreaterThanOrEqualTo(Integer price){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<ProductDao> priceLessThanOrEqualTo(Integer price){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<ProductDao> titleLike(String titlePart){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

}
