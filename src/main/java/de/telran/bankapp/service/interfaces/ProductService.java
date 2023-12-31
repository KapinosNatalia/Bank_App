package de.telran.bankapp.service.interfaces;

import de.telran.bankapp.dto.ProductDto;
import de.telran.bankapp.dto.ProductWithManagerAndQuantityDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto getProductById(UUID id);

    List<ProductDto> getAllProducts();

    List<ProductWithManagerAndQuantityDto> findAllProductsWhereAgreementsQuantityMoreThan(int quantity);

    List<ProductWithManagerAndQuantityDto> getProductsWithQuantityOfUsing();

}
