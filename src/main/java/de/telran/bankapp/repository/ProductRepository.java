package de.telran.bankapp.repository;

import de.telran.bankapp.dto.ProductWithManagerAndQuantityDto;
import de.telran.bankapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "SELECT new de.telran.bankapp.dto.ProductWithManagerAndQuantityDto(a.product.id, a.product.name, CONCAT(a.product.manager.firstName, ' ', a.product.manager.lastName), COUNT(a.product.id)) FROM Agreement a GROUP BY a.product HAVING COUNT(a.product.id) > :quantity")
    List<ProductWithManagerAndQuantityDto> findAllProductsWhereAgreementsQuantityMoreThan(int quantity);

    @Query(value = "SELECT new de.telran.bankapp.dto.ProductWithManagerAndQuantityDto(p.id, p.name, CONCAT(p.manager.firstName, ' ', p.manager.lastName), COALESCE(COUNT(a.product), 0)) FROM Product p LEFT JOIN Agreement a ON p = a.product GROUP BY p, a.product")
    List<ProductWithManagerAndQuantityDto> getProductsWithQuantityOfUsing();

}
