package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbsDto implements Serializable {

    private Long id;

    private String name;

    private Boolean active;
}
