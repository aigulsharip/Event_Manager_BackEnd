package kz.daracademy.model.event;


import kz.daracademy.model.category.Category;
import kz.daracademy.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
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
    Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;








}
