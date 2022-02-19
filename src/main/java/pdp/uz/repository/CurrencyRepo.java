package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Currency;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    @Query("select c from Currency c where c.active = true")
    List<Currency> findAllByActiveTrue();

    Optional<Currency> findByNameAndActiveTrue(String name);
}
