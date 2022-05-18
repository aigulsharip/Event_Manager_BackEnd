package kz.daracademy.model.event;

import kz.daracademy.model.category.CategoryEntity;
import kz.daracademy.model.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventNotificationInfo {

    private String name;
    private String email;

    private String title;
    private Date startDateTime;
    private Date postedDate;

}
