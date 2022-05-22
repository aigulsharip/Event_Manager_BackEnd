package kz.daracademy.model.favorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRequest {
    private String favoriteId;
    private String userId;
    private String eventId;
}
