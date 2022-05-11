package kz.daracademy.controller;

import kz.daracademy.model.EventRequest;
import kz.daracademy.model.EventResponse;
import kz.daracademy.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

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
    public EventResponse getEventById (@RequestParam String eventId){
        return eventService.getEventById(eventId);
    }

    @DeleteMapping
    public void deleteEvent (@RequestParam String eventId) {
        eventService.deleteEventByEventId(eventId);
    }

    @GetMapping("/filter")
    public List<EventResponse> filterEventByCategory(@RequestParam String categoryId) {
        return eventService.getEventsByCategoryId(categoryId);
    }

    @GetMapping("/filter/categoryName")
    public List<EventResponse> filterEventByCategoryName(@RequestParam String categoryName) {
        return eventService.getEventsByCategoryName(categoryName);
    }



    @GetMapping("/section")
    public List<EventResponse> getSections(@RequestParam String sectionName) {
        if (sectionName.equals("popular")) {
            return eventService.getPopularEvents();
        } else if (sectionName.equals("upcoming")) {
            return eventService.getUpcomingEvents();
        }
        else if (sectionName.equals("new")) {
            return eventService.getNewEvents();
        }
        return eventService.getAllEvents();
    }





}
