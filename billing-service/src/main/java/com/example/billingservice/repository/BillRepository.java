package com.example.billingservice.repository;

import com.example.billingservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Integer> {


}
