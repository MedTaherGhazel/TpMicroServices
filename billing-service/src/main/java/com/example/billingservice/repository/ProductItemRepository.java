package com.example.billingservice.repository;

import com.example.billingservice.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {
        Integer findByBillId(Integer id);
    }
