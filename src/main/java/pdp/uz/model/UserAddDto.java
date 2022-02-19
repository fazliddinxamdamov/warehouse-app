package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserAddDto implements Serializable {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private List<Long> warehouseIds;

    public Boolean active = false;
}
