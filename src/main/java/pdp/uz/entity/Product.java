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
@Table(name = "product")
public class Product extends AbsEntity {

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurement measurement;
}
