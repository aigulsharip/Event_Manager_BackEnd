package kz.daracademy.controller;

import kz.daracademy.model.dislike.DislikeRequest;
import kz.daracademy.model.dislike.DislikeResponse;
import kz.daracademy.service.dislike.DislikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dislike")
public class DislikeController {

    @Autowired
    private DislikeServiceImpl dislikeService;

    @PostMapping
    public ResponseEntity<String> createLike(@RequestBody DislikeRequest dislikeRequest) {
        DislikeResponse dislikeResponse = dislikeService.createDislike(dislikeRequest);
        if (dislikeResponse == null) {
            return new ResponseEntity<>("Dont disliked", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    private int getDislikesByEventId(@RequestParam String eventId){
        return dislikeService.getDislikesByEventId(eventId);
    }

    @GetMapping
    public boolean getDislikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        return dislikeService.getDislikeByEventIdAndUserId(eventId, userId);
    }

    @DeleteMapping
    public void deleteDislikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId){
        dislikeService.deleteDislikeByEventIdAndUserId(eventId, userId);
    }
}
