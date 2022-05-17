package kz.daracademy.service.event;

import kz.daracademy.model.event.EventRequest;
import kz.daracademy.model.event.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);

    EventResponse updateEvent(EventRequest eventRequest);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(String eventId);

    void deleteEventByEventId(String eventId);

    List<EventResponse> getEventsByCategoryId(String categoryId);


    List<EventResponse> getEventsByCategoryName(String categoryName);

    List<EventResponse> getPopularEvents();

    List<EventResponse> getUpcomingEvents();

    List<EventResponse> getNewEvents();


}
