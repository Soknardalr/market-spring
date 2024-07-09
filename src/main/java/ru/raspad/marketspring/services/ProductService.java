package ru.raspad.marketspring.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.entity.ProductDao;
import ru.raspad.marketspring.exceptions.EntityAlreadyExistsException;
import ru.raspad.marketspring.exceptions.ResourceNotFoundException;
import ru.raspad.marketspring.exceptions.ValidateException;
import ru.raspad.marketspring.mappers.CustomerMapper;
import ru.raspad.marketspring.mappers.ProductMapper;
import ru.raspad.marketspring.repositories.ProductRepository;
import ru.raspad.marketspring.repositories.specification.ProductSpecification;
import ru.raspad.marketspring.validators.ProductValidator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator validator;

    public Page<ProductDto> find(Integer p, Integer minPrice, Integer maxPrice, String partTitle){
        Specification<ProductDao> spec = Specification.where(null);
        // select p from Products p where true
        if (minPrice != null){
            spec = spec.and(ProductSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        // select p from Products p where p.price > ?
           if (maxPrice != null){
            spec = spec.and(ProductSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        // select p from Products p where p.price > ? AND p.price < ?
        if (partTitle != null){
            spec = spec.and(ProductSpecification.titleLike(partTitle));
        }
        // select p from Products p where p.price > ? AND p.price < ? AND p.title like %partTitle%

        return productRepository.findAll(spec, PageRequest.of(p - 1, 15))
                .map(ProductMapper.INSTANCE::toDto);
    }


//    public ResponseEntity<?> getProduct(Long id){
//        Optional<ProductDao> product = productRepository.findById(id);
//        if (product.isEmpty()){
//            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Product not found: id " + id), HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(ProductMapper.INSTANCE.toDto(product.get()), HttpStatus.OK);
//        }
//    }
    public ProductDto getProduct(Long id) {
        return productRepository.findById(id).map(ProductMapper.INSTANCE::toDto).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public void addProduct(Long id, String title) {

    }

    public void addProduct(ProductDto productDto) {
        validator.validate(productDto);
        if (productRepository.existsProductDaoByTitle(productDto.getTitle())){
            throw new EntityAlreadyExistsException("This product already exists");
        } else {
            productRepository.save(ProductMapper.INSTANCE.toDao(productDto));
        }
    }
    @Transactional
    public void updateProduct(ProductDto productDto){
        if (productRepository.existsProductDaoById(productDto.getId())){
            throw new ValidateException(Collections.singletonList("No product with this id"));
        }
        ProductDao product = productRepository.getReferenceById(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setCustomers(productDto.getCustomerDtos().stream().map(CustomerMapper.INSTANCE::toDao).collect(Collectors.toList()));

    }

    public void changePrice(Long id, Integer delta) {
        ProductDao productDao = productRepository.findById(id).orElseThrow();
        productDao.setPrice(productDao.getPrice() + delta);
        productRepository.save(productDao);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateTitle(Long id, ProductDto productDto) {
        ProductDao product = productRepository.getReferenceById(productDto.getId());
        product.setTitle(productDto.getTitle());
    }
}
