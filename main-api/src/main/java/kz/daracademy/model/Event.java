package kz.daracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    private String eventId;

    private String title;

    private String description;

    private Date startDateTime;

    private Date endDateTime;

    private List<String> pictures;

    private Integer votes;

    private Integer dislikes;

    Date postedDate;


    private Category category;

    private UserDetailsModel user;
}
