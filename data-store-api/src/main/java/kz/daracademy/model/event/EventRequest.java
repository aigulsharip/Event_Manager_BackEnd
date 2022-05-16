package kz.daracademy.model.event;

import kz.daracademy.model.category.Category;
import kz.daracademy.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventRequest {

    private String eventId;

    private String title;

    private String description;

    private Date startDateTime;

    private Date endDateTime;

    private List<String> pictures;

    private Integer votes;

    private Category category;

    private User user;




}