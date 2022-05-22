package kz.daracademy.model.favorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite_events")
public class FavoriteEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String favoriteId;
    @Column
    private String userId;
    @Column
    private String eventId;
}
