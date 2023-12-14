package com.example.billingservice.Service;

import com.example.billingservice.model.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="inventory-service",url = "localhost:8082")
public interface InventoryServiceClient{
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable("id") Integer id);
    @GetMapping("/products")
    PagedModel<Product> findAll();
}