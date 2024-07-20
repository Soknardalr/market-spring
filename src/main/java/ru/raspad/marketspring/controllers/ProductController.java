package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.raspad.marketspring.dto.ProductDto;
import ru.raspad.marketspring.services.ProductService;

@RestController
@RequestMapping("/app/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;



    @GetMapping()
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                           @RequestParam(name = "min_price", required = false) Integer minPrice,
                                           @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                           @RequestParam(name = "title_part", required = false) String titlePart) {
        if (page < 1) {
            page = 1;
        }
        return service.find(page, minPrice, maxPrice, titlePart);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    // todo uncorrected work
    @PutMapping()
    public void updateProduct(@RequestBody ProductDto product) {
        service.updateProduct(product);
    }

    @Deprecated(forRemoval = true)
    @GetMapping("/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        service.changePrice(productId, delta);
    }

    @PostMapping()
    public void postProduct(@RequestBody ProductDto productDto) {
        service.addProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PatchMapping("/{id}/title")
    public void patchTitle(@PathVariable Long id, @RequestBody ProductDto productDto){
        service.updateTitle(id, productDto);
    }
}
