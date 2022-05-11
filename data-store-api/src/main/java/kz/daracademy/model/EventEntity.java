package kz.daracademy.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column
    String picture;
    @Column
    Integer votes;

    @ManyToOne(fetch = FetchType.EAGER)
    Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;








}
