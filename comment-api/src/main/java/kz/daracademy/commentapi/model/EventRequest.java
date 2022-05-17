package kz.daracademy.commentapi.model;

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
    private Integer votes;
}
