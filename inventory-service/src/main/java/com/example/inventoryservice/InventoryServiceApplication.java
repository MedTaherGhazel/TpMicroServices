package com.example.inventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
class Product{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long> {}
@Projection(name = "fullProduct",types = Product.class)
interface ProductProjection extends Projection {
    public Long getId();
    public String getName ();
    public Double getPrice ();
}
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository){
    return args->{
        productRepository.save(new Product(null,"Computer Desk Top HP",900));
        productRepository.save(new Product(null,"Printer Epson",80));
        productRepository.save(new Product(null,"MacBook Pro Lap Top",1800));
        productRepository.findAll().forEach(System.out::println);};
}

}
