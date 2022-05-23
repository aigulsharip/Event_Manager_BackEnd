package kz.daracademy.model.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeResponse {
    private String likeId;
    private String eventId;
    private String userId;
}
