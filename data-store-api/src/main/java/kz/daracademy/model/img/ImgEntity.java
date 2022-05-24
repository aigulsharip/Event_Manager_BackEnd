package kz.daracademy.model.img;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@Table(name = "images_table")
@NoArgsConstructor
@AllArgsConstructor
public class ImgEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String imgId;
    @Column
    private String eventId;
    @Column
    private String fileName;
    @Column
    private String fileType;
    @Column
    private boolean mainPhoto;
    @Lob
    private byte[] data;
}
