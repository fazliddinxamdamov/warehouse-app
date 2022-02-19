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
@Table(name = "attachment_content")
public class AttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private Attachment attachment;
}
