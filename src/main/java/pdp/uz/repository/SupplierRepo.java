package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Supplier;

import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    @Query("select s from Supplier s where s.phoneNumber = ?1 and s.active = true")
    Optional<Supplier> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
