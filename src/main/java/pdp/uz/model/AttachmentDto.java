package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AttachmentDto implements Serializable {

    private Long id;

    private String name;

    private Long size;

    private String contentType;
}
