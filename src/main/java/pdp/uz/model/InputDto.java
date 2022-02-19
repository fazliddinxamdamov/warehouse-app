package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;
import pdp.uz.entity.Supplier;
import pdp.uz.entity.Warehouse;

import java.time.LocalDateTime;

@Setter
@Getter
public class InputDto {
    private Long id;

    private LocalDateTime date;

    private String featureNumber;

    private String code;

    private Warehouse warehouse;

    private Supplier supplier;

    private String currency;
}
