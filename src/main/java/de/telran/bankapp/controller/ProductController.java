package de.telran.bankapp.controller;

import de.telran.bankapp.dto.ProductDto;
import de.telran.bankapp.dto.ProductWithManagerAndQuantityDto;
import de.telran.bankapp.entity.Product;
import de.telran.bankapp.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") UUID productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/find-all-products-where-agreement-quantity-more-than/{quantity}")
    public ResponseEntity<List<ProductWithManagerAndQuantityDto>> findAllProductsWhereAgreementsQuantityMoreThan(@PathVariable("quantity") int quantity) {
        return ResponseEntity.ok(productService.findAllProductsWhereAgreementsQuantityMoreThan(quantity));
    }

    @GetMapping("/get-products-with-quantity-of-using")
    public ResponseEntity<List<ProductWithManagerAndQuantityDto>> getProductsWithQuantityOfUsing() {
        return ResponseEntity.ok(productService.getProductsWithQuantityOfUsing());
    }

}
