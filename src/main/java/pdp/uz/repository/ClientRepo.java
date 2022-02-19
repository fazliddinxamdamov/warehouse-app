package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.phoneNumber = ?1 and c.active = true")
    Optional<Client> findByPhoneNumber(String phoneNumber);
}
