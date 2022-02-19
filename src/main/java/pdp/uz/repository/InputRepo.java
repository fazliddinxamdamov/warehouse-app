package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Input;

@Repository
public interface InputRepo extends JpaRepository<Input, Long> {
}
