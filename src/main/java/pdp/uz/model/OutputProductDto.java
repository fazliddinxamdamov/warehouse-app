package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;
import pdp.uz.entity.Output;
import pdp.uz.entity.Product;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
public class OutputProductDto {
    private Long id;

    private Double amount;

    private Double price;

    private String product;

    private String measurement;

    private String category;

    private Long outputId;

    private LocalDateTime date;

}
