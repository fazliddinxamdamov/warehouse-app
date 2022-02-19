package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductAddDto implements Serializable {

    private String name;

    private Long categoryId;

    private Long measurementId;

    private Long attachmentId;
}
