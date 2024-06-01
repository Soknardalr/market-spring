package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.entity.ProductDao;
import ru.raspad.marketspring.mappers.ProductMapper;
import ru.raspad.marketspring.mappers.ProductMapperImpl;
import ru.raspad.marketspring.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductDto> getProductsByPriceBetween(Integer min, Integer max){
        return productRepository.getProductsByPriceBetween(min, max).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
    public List<ProductDto> getProductsMaxPrice(Integer maxPrice){
        return productRepository.getProductDaosByPriceIsLessThan(maxPrice).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
    public List<ProductDto> getProductsMinPrice(Integer minPrice){
        return productRepository.getProductDaosByPriceIsGreaterThan(minPrice).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

//    public ProductDto getProduct(Long id){
//        return ProductMapper.INSTANCE.toDto(productRepository.findById(id).orElseThrow());
//    }
    public ProductDto getProduct(Long id){
        return ProductMapper.INSTANCE.toDto(productRepository.findById(id).orElseThrow());
    }

    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public void addProduct(Long id, String title){

    }

    public void addProduct(ProductDto productDto) {
        productRepository.save(ProductMapper.INSTANCE.toDao(productDto));
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
