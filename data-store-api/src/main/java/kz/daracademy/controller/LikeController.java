package kz.daracademy.controller;

import kz.daracademy.model.like.LikeRequest;
import kz.daracademy.model.like.LikeResponse;
import kz.daracademy.service.like.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeServiceImpl likeService;

    @PostMapping
    public ResponseEntity<String> createLike(@RequestBody LikeRequest likeRequest) {
        LikeResponse likeResponse = likeService.createLike(likeRequest);
        if (likeResponse == null) {
            return new ResponseEntity<>("Dont liked", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    private int getLikesByEventId(@RequestParam String eventId){
        return likeService.getLikesByEventId(eventId);
    }

    @GetMapping
    public boolean getLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        return likeService.getLikeByEventIdAndUserId(eventId, userId);
    }

    @DeleteMapping
    public void deleteLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        likeService.deleteLikeByEventIdAndUserId(eventId, userId);
    }
}
