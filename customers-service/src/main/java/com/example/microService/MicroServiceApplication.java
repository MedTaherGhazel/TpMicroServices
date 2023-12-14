package com.example.microService;

import com.example.microService.Models.Customers;
import com.example.microService.repositories.CustomersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//@EnableWebSecurity
@SpringBootApplication
public class MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}
  @Bean
	CommandLineRunner commandLineRunner (CustomersRepository customersRepository) {
	 return args -> {
		 customersRepository.save(new Customers(null,"eni","contact@eni.tn"));
		 customersRepository.save(new Customers(null,"FST","contact@fst.tn"));
		 customersRepository.save(new Customers(null,"ENSI","contact@ensi.tn"));
		 customersRepository.findAll().forEach(System.out::println);
};
}

}
