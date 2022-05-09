package kz.daracademy.service;

import kz.daracademy.model.EventRequest;
import kz.daracademy.model.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);

    EventResponse updateEvent(EventRequest eventRequest);

    List<EventResponse> getAllEvents();

    EventResponse getEventById (String eventId);
    void deleteEventByEventId (String eventId);

}
