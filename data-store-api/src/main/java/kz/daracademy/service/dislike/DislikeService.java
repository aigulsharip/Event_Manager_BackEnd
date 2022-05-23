package kz.daracademy.service.dislike;


import kz.daracademy.model.dislike.DislikeRequest;
import kz.daracademy.model.dislike.DislikeResponse;

public interface DislikeService {
    DislikeResponse createDislike(DislikeRequest dislikeRequest);

    int getDislikesByEventId(String eventId);

    boolean getDislikeByEventIdAndUserId(String eventId, String userId);

    void deleteDislikeByEventIdAndUserId(String eventId, String userId);
}
