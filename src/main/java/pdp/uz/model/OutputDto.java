package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;
import pdp.uz.entity.Client;
import pdp.uz.entity.Warehouse;

import java.time.LocalDateTime;

@Setter
@Getter
public class OutputDto {
    private Long id;

    private LocalDateTime date;

    private String featureNumber;

    private String code;

    private Warehouse warehouse;

    private String currency;

    private Client client;
}
