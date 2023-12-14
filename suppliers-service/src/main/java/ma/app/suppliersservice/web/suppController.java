package ma.app.suppliersservice.web;




import ma.app.suppliersservice.entity.Supplier;
import ma.app.suppliersservice.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/all")
public class suppController {
    @Autowired
    private SupplierRepo supRepository;


    @GetMapping
    public List<Supplier> getsupliers() {

        return supRepository.findAll();
    }

}
