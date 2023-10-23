package de.telran.bankapp.service.implementations;

import de.telran.bankapp.dto.ProductDto;
import de.telran.bankapp.dto.ProductWithManagerAndQuantityDto;
import de.telran.bankapp.entity.Product;
import de.telran.bankapp.mapper.ProductMapper;
import de.telran.bankapp.repository.ProductRepository;
import de.telran.bankapp.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product getProductById(UUID id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public List<ProductWithManagerAndQuantityDto> findAllProductsWhereAgreementsQuantityMoreThan(int quantity) {
        return productRepository.findAllProductsWhereAgreementsQuantityMoreThan(quantity);
    }

    @Override
    public List<ProductWithManagerAndQuantityDto> getProductsWithQuantityOfUsing() {
        return productRepository.getProductsWithQuantityOfUsing();
    }
}
