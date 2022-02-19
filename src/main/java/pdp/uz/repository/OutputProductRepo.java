package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.OutputProduct;

@Repository
public interface OutputProductRepo extends JpaRepository<OutputProduct, Long> {
}
