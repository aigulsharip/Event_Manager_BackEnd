package kz.daracademy.controller;

import kz.daracademy.feign.DataStoreFeign;
import kz.daracademy.model.Event;
import kz.daracademy.model.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private DataStoreFeign dataStoreFeign;

    @PostMapping
    public Event  createEvent(@RequestBody Event event) {
        UserDetailsModel principal = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        event.setUser(principal);
        return dataStoreFeign.createEvent(event);
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event, @RequestParam String eventId) {
        event.setEventId(eventId);
        return dataStoreFeign.updateEvent(event, eventId);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return dataStoreFeign.getAllEvents();
    }

    @GetMapping()
    public Event getEventById(@RequestParam String eventId) {
        return  dataStoreFeign.getEventById(eventId);
    }

    @DeleteMapping
    public void deleteEvent(@RequestParam String eventId) {
        dataStoreFeign.deleteEvent(eventId);
    }

    @GetMapping("/event/user")
    List<Event> getEventsByUserId(@RequestParam String userId){
        return dataStoreFeign.getEventsByUserId(userId);
    }

    @GetMapping("/filter")
    List<Event> filterEventByCategory(@RequestParam String categoryId){
        return dataStoreFeign.filterEventByCategory(categoryId);
    }

    @GetMapping("/filter/categoryName")
    List<Event> filterEventsByCategoryName(@RequestParam String categoryName){
        return dataStoreFeign.filterEventsByCategoryName(categoryName);
    }

    @GetMapping("/section")
    List<Event> getSections (@RequestParam String sectionName){
        return dataStoreFeign.getSections(sectionName);
    }

}
