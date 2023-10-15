package de.telran.bankapp.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductWithManagerAndQuantityDto {
    UUID product_id;
    //String product_id;
    String product_name;
    String manager_name;
    //String quantity_of_use;
    long quantity_of_use;

    public ProductWithManagerAndQuantityDto(UUID product_id, String product_name, String manager_name, long quantity_of_use) {
    //public ProductWithManagerAndQuantityDto(String product_id, String product_name, String manager_name, String quantity_of_use) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.manager_name = manager_name;
        this.quantity_of_use = quantity_of_use;
    }
}
