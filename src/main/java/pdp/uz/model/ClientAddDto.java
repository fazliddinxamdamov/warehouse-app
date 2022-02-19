package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
public class ClientAddDto extends AbsAddDto {

    @Size(min = 7, message = "Phone number size must be than more 7")
    @Size(max = 12, message = "Phone number size must be than less 12")
    private String phoneNumber;
}
