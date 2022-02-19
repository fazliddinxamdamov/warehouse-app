package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InputAddDto {
    private String featureNumber;

    private Long warehouseId;

    private Long supplierId;

    private Long currencyId;
}
