package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;
import pdp.uz.entity.Warehouse;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;

    public Boolean active;

    private Set<Warehouse> warehouses;
}
