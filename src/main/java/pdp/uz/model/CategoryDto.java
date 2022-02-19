package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;
import pdp.uz.entity.Category;

@Getter
@Setter
public class CategoryDto extends AbsDto {

    private Category parentCategory;
}
