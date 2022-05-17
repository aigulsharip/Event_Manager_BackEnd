package kz.daracademy.model.event;


import kz.daracademy.model.category.CategoryEntity;
import kz.daracademy.model.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "events_table")
@NoArgsConstructor
@AllArgsConstructor

public class EventEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    String eventId;
    @Column
    String title;
    @Column
    String description;
    @Column
    Date startDateTime;
    @Column
    Date endDateTime;
    @ElementCollection
    List<String> pictures;
    @Column
    Integer votes;

    @ManyToOne(fetch = FetchType.EAGER)
    CategoryEntity category;
    @ManyToOne(fetch = FetchType.EAGER)
    UserEntity user;


}
