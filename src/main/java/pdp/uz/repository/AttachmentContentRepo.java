package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.AttachmentContent;

@Repository
public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, Long> {
}
