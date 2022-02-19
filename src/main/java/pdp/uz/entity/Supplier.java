package pdp.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier extends AbsEntity {

    @Column(name = "phone_number")
    private String phoneNumber;
}
