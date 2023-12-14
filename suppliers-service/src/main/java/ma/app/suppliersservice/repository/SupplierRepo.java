package ma.app.suppliersservice.repository;

import ma.app.suppliersservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplierRepo  extends JpaRepository<Supplier,Long> {

}