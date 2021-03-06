package kz.daracademy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.daracademy.model.event.EventNotificationInfo;
import kz.daracademy.model.event.EventRequest;
import kz.daracademy.model.event.EventResponse;
import kz.daracademy.service.event.EventServiceImpl;
import kz.daracademy.service.message.SendServiceImpl;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private SendServiceImpl sendService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/check")
    public String check() {
        return "data-store-api is working";
    }

    @PostMapping
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) throws JsonProcessingException {
        return eventService.createEvent(eventRequest);
    }


    @PutMapping
    public EventResponse updateEvent(@RequestBody EventRequest eventRequest, @RequestParam String eventId) {
        eventRequest.setEventId(eventId);
        return eventService.updateEvent(eventRequest);
    }

    @GetMapping("/all")
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/user")
    public List<EventResponse> getEventsByUserId(@RequestParam String userId) {
        return eventService.getEventsByUserId(userId);
    }

    @GetMapping("/userF")
    public List<EventResponse> getEventsByUserIdF(@RequestParam String userIdF) {
        return eventService.getEventsByUserIdF(userIdF);
    }



    @GetMapping()
    public EventResponse getEventById(@RequestParam String eventId) {
        return eventService.getEventById(eventId);
    }

    @DeleteMapping
    public void deleteEvent(@RequestParam String eventId) {
        eventService.deleteEventByEventId(eventId);
    }

    // filtration by category_id (from 1 to 5)
    @GetMapping("/filter")
    public List<EventResponse> filterEventByCategory(@RequestParam String categoryId) {
        return eventService.getEventsByCategoryId(categoryId);
    }

    // filtration by categoryName (????????????????, ????????????????, ??????????, ????????, ??????????)
    @GetMapping("/filter/categoryName")
    public List<EventResponse> filterEventByCategoryName(@RequestParam String categoryName) {
        return eventService.getEventsByCategoryName(categoryName);
    }

    //sectionName = all or anything to display the list all events
    @GetMapping("/section")
    public List<EventResponse> getSections(@RequestParam String sectionName) {
        if (sectionName.equals("popular")) {
            return eventService.getPopularEvents();
        } else if (sectionName.equals("upcoming")) {
            return eventService.getUpcomingEvents();
        } else if (sectionName.equals("new")) {
            return eventService.getNewEvents();
        }
        return eventService.getAllEvents();
    }

    @PostMapping("/notification/send-event")
    public ResponseEntity<String> sendEventData(@RequestParam String eventId) throws JsonProcessingException {
        EventNotificationInfo eventNotificationInfo = eventService.prepareEventInfoForNotification(eventId);
        sendService.send(objectMapper.writeValueAsString(eventNotificationInfo));
        return new ResponseEntity<>("Mail Sent Succesfully", HttpStatus.OK);
    }





}
