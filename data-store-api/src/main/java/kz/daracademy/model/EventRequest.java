package kz.daracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventRequest {

    private String eventId;

    private String title;

    private String description;

    private Date startDateTime;

    private Date endDateTime;

    private String picture;

    private Integer votes;

    private Category category;

    private User user;




}
