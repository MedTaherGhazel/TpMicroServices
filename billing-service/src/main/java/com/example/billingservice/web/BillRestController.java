package com.example.billingservice.web;


import com.example.billingservice.Service.CustomerServiceClient;
import com.example.billingservice.Service.InventoryServiceClient;
import com.example.billingservice.model.Bill;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillRestController {

    private final BillRepository billRepository;

    private final ProductItemRepository productItemRepository;

    private final CustomerServiceClient customerRestClient;

    private final InventoryServiceClient inventoryServiceClient;

    @GetMapping("/bills/full/{id}")
    Bill getBill(@PathVariable(name="id") Long id) {
        Bill bill=billRepository.findById(Math.toIntExact (id)).get();
        bill.setCustomer(customerRestClient.findCustomerById((int) bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(Math.toIntExact (id)));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(inventoryServiceClient.findProductById((int) pi.getProductID()));
        });
        return bill; }
}
