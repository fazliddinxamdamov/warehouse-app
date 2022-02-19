package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;
import pdp.uz.entity.Attachment;
import pdp.uz.entity.Category;
import pdp.uz.entity.Measurement;

@Getter
@Setter
public class ProductDto extends AbsDto {

    private String code;

    private Category category;

    private Attachment attachment;

    private Measurement measurement;
}
