package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class InputProductDto {
    private Long id;

    private Double amount;

    private Double price;

    private LocalDate expireDate;

    private String productCode;

    private String product;

    private String measurement;

    private String category;

    private Long inputId;

    private LocalDateTime date;
}
