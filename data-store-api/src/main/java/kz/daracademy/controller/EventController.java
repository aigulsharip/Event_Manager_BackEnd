package kz.daracademy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.daracademy.model.event.EventNotificationInfo;
import kz.daracademy.model.event.EventRequest;
import kz.daracademy.model.event.EventResponse;
import kz.daracademy.service.event.EventService;
import kz.daracademy.service.message.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SendService sendService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/check")
    public String check() {
        return "data-store-api is working";
    }

    @PostMapping
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
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

    // filtration by categoryName (Концерты, Выставки, Театр, Кино, Спорт)
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
        EventNotificationInfo eventNotificationInfo = new EventNotificationInfo();
        EventResponse event = eventService.getEventById(eventId);
        eventNotificationInfo.setTitle(event.getTitle());
        eventNotificationInfo.setName(event.getUser().getFullName());
        eventNotificationInfo.setEmail(event.getUser().getEmail());
        eventNotificationInfo.setPostedDate(event.getPostedDate());
        eventNotificationInfo.setStartDateTime(event.getStartDateTime());

        sendService.send(objectMapper.writeValueAsString(eventNotificationInfo));
        return new ResponseEntity<>("Mail Sent Succesfully", HttpStatus.OK);
    }


}
