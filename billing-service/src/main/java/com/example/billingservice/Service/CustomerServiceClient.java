package com.example.billingservice.Service;

import com.example.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service",url = "http://localhost:8086/")
public interface CustomerServiceClient{
    @GetMapping("customerses/{id}")
    public  Customer findCustomerById(@PathVariable() Integer id);
}