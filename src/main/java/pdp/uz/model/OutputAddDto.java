package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputAddDto {
    private String featureNumber;

    private Long warehouseId;

    private Long currencyId;

    private Long clientId;
}
