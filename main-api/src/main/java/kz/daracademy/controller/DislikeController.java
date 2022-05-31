package kz.daracademy.controller;


import kz.daracademy.feign.DataStoreFeign;
import kz.daracademy.model.Dislike;
import kz.daracademy.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dislike")
public class DislikeController {

    @Autowired
    private DataStoreFeign dataStoreFeign;


    @PostMapping
    public ResponseEntity<String> createDislike(@RequestBody Dislike like) {
        return dataStoreFeign.createDislike(like);

    }

    @GetMapping("/all")
    private int getLikesByEventId(@RequestParam String eventId){
        return dataStoreFeign.getDislikesByEventId(eventId);
    }

    @GetMapping
    public boolean getLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        return dataStoreFeign.getDislikeByEventIdAndUserId(eventId, userId);
    }

    @DeleteMapping
    public void deleteLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        dataStoreFeign.deleteDislikeByEventIdAndUserId(eventId, userId);
    }
}
