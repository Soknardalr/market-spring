package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.entity.ProductDao;
import ru.raspad.marketspring.exceptions.AppError;
import ru.raspad.marketspring.exceptions.EntityAlreadyExistsException;
import ru.raspad.marketspring.exceptions.ResourceNotFoundException;
import ru.raspad.marketspring.mappers.ProductMapper;
import ru.raspad.marketspring.mappers.ProductMapperImpl;
import ru.raspad.marketspring.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> getProductsByPriceBetween(Integer min, Integer max) {
        return productRepository.getProductsByPriceBetween(min, max).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public List<ProductDto> getProductsMaxPrice(Integer maxPrice) {
        return productRepository.getProductDaosByPriceIsLessThan(maxPrice).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public List<ProductDto> getProductsMinPrice(Integer minPrice) {
        return productRepository.getProductDaosByPriceIsGreaterThan(minPrice).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    //    public ProductDto getProduct(Long id){
//        return ProductMapper.INSTANCE.toDto(productRepository.findById(id).orElseThrow());
//    }
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
        if (productRepository.existsProductDaoByTitle(productDto.getTitle())){
            throw new EntityAlreadyExistsException("This product already exists");
        } else {
            productRepository.save(ProductMapper.INSTANCE.toDao(productDto));
        }

    }

    public void changePrice(Long id, Integer delta) {
        ProductDao productDao = productRepository.findById(id).orElseThrow();
        productDao.setPrice(productDao.getPrice() + delta);
        productRepository.save(productDao);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
