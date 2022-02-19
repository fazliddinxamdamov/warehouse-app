package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.InputProduct;
import pdp.uz.model.resp.ProductReport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InputProductRepo extends JpaRepository<InputProduct, Long> {
    @Query(value = "select p.name, ip.amount, ip.price, (ip.amount * ip.price) as totalAmount\n" +
            "from input_product ip\n" +
            "         join input i on i.id = ip.input_id\n" +
            "         join product p on ip.product_id = p.id\n" +
            "where date(i.date) = :date", nativeQuery = true)
    List<ProductReport> findAllByDate(LocalDate date);
}
