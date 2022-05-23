package kz.daracademy.model.dislike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DislikeRequest {
    private String dislikeId;
    private String eventId;
    private String userId;
}
