package pdp.uz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbsAddDto implements Serializable {

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "About Me must be than more 0 characters")
    private String name;

    private boolean active;
}
