package kz.daracademy.model.event;

import kz.daracademy.model.category.CategoryEntity;
import kz.daracademy.model.img.ImgRequest;
import kz.daracademy.model.img.ImgResponse;
import kz.daracademy.model.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventResponse {

    private String eventId;

    private String title;

    private String description;

    private Date startDateTime;

    private Date endDateTime;

    private List<ImgResponse> pictures;

    Integer votes;

    Date postedDate;


    private CategoryEntity category;

    private UserEntity user;

}
