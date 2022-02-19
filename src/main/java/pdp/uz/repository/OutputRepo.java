package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Output;

@Repository
public interface OutputRepo extends JpaRepository<Output, Long> {
}
