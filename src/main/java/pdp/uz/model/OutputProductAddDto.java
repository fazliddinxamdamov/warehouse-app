package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputProductAddDto {
    private Double amount;
    private Double price;
    private Long productId;
    private Long outputId;
}
