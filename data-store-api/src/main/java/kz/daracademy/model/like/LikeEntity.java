package kz.daracademy.model.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "like_table")
public class LikeEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String likeId;

    private String eventId;
    private String userId;

}
