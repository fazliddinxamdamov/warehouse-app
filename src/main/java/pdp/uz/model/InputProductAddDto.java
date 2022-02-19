package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Setter
@Getter
public class InputProductAddDto {

    @Min(value = 100, message = "Amount should not be less than 100")
    @Max(value = 100000, message = "Amount should not be greater than 100000")
    private Double amount;

    private Double price;

    private LocalDate expireDate;

    private Long productId;

    private Long inputId;
}
