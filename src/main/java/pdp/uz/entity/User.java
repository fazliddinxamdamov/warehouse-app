package pdp.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "password")
    private String password;

    @Column(name = "active", columnDefinition = "boolean default true")
    public Boolean active;

    @ManyToMany
    @JoinTable(name = "user_warehouse",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id", referencedColumnName = "id"))
    private Set<Warehouse> warehouses;
}
