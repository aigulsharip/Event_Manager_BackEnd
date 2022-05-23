package kz.daracademy.model.dislike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dislike_table")
public class DislikeEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String dislikeId;

    private String eventId;
    private String userId;

}
