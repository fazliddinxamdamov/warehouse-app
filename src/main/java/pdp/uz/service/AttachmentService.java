package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.entity.Attachment;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.AttachmentDto;

import java.io.IOException;

public interface AttachmentService {

    ResponseEntity<ApiResponse<AttachmentDto>> upload(MultipartFile file) throws IOException;

    Attachment validate(Long id);

}
