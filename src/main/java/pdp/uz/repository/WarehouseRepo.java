package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Warehouse;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

    // select * from warehouse t where t.name = :name
    Optional<Warehouse> findByName(String name);

    @Query(value = "select t from Warehouse t where t.active = true")
    List<Warehouse> findAll();
}
