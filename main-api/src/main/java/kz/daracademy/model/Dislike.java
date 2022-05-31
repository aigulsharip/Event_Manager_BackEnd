package kz.daracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dislike {
    private String dislikeId;
    private String eventId;
    private String userId;
}
