package pdp.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "input")
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDateTime date;

    @Column(name = "feature_number")
    private String featureNumber;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;
}
