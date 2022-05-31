package kz.daracademy.controller;

import kz.daracademy.feign.DataStoreFeign;
import kz.daracademy.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private DataStoreFeign dataStoreFeign;


    @PostMapping
    public ResponseEntity<String> createLike(@RequestBody Like like) {
        return dataStoreFeign.createLike(like);

    }

    @GetMapping("/all")
    private int getLikesByEventId(@RequestParam String eventId){
        return dataStoreFeign.getLikesByEventId(eventId);
    }

    @GetMapping
    public boolean getLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        return dataStoreFeign.getLikeByEventIdAndUserId(eventId, userId);
    }

    @DeleteMapping
    public void deleteLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        dataStoreFeign.deleteLikeByEventIdAndUserId(eventId, userId);
    }
}
