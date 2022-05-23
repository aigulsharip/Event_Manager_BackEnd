package kz.daracademy.service.like;


import kz.daracademy.model.like.LikeRequest;
import kz.daracademy.model.like.LikeResponse;

import java.util.List;

public interface LikeService {
    LikeResponse createLike(LikeRequest likeRequest);

    int getLikesByEventId(String eventId);

    boolean getLikeByEventIdAndUserId(String eventId, String userId);

    void deleteLikeByEventIdAndUserId(String eventId, String userId);
}
