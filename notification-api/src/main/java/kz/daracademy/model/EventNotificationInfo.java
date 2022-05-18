package kz.daracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventNotificationInfo {

    private String title;
    private Date startDateTime;
    private Date postedDate;
    private String name;
    private String email;

}
