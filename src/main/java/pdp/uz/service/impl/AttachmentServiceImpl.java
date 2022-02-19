package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.entity.Attachment;
import pdp.uz.entity.AttachmentContent;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.AttachmentDto;
import pdp.uz.repository.AttachmentContentRepo;
import pdp.uz.repository.AttachmentRepo;
import pdp.uz.service.AttachmentService;
import static pdp.uz.model.ApiResponse.response;

import java.io.IOException;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo attachmentContentRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepo attachmentRepo, AttachmentContentRepo attachmentContentRepo, MapstructMapper mapstructMapper) {
        this.attachmentRepo = attachmentRepo;
        this.attachmentContentRepo = attachmentContentRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<AttachmentDto>> upload(MultipartFile file) throws IOException {
        // Attachment view object
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());

        // Attachment view save to database
        Attachment createdAttachment = attachmentRepo.save(attachment);

        // Attachment content object
        AttachmentContent content = new AttachmentContent();
        content.setData(file.getBytes());
        content.setAttachment(createdAttachment);

        // Attachment content save to database
        attachmentContentRepo.save(content);

        return response(mapstructMapper.toAttachmentDto(createdAttachment), HttpStatus.CREATED);
    }

    @Override
    public Attachment validate(Long id) {
        Optional<Attachment> attachmentOpt = attachmentRepo.findById(id);
        if (!attachmentOpt.isPresent()) {
            throw new RuntimeException("Attachment id = " + id + ", not found!");
        }
        return attachmentOpt.get();
    }
}
