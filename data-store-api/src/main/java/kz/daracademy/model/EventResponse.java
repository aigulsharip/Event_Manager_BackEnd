package kz.daracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventResponse {

    private String eventId;

    private String title;

    private String description;

    private Date startDateTime;

    private Date endDateTime;

    private String picture;

    Integer votes;

    private Category category;

    private User user;

}
