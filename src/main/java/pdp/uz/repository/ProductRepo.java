package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Override
    @Query(value = "select t from Product t where t.active = true")
    List<Product> findAll();

    @Query(value = "select t from Product t where t.category.id = :categoryId and t.active = true")
    List<Product> findAllByCategoryId(Long categoryId);

    @Query(value = "select t from Product t where t.measurement.id = :measurementId and t.active = true")
    List<Product> findAllByMeasurementId(Long measurementId);

    Optional<Product> findByName(String name);
}
