package com.example.microService.Models;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProjection", types = {Customers.class})
public interface CustomersProjection extends Projection {
    public Long getId();
    public String getName();
    public String getEmail();
}