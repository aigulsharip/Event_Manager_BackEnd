package kz.daracademy.service;

import kz.daracademy.model.EventEntity;
import kz.daracademy.model.EventRequest;
import kz.daracademy.model.EventResponse;
import kz.daracademy.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        eventRequest.setEventId(UUID.randomUUID().toString());
        EventEntity eventEntity = modelMapper.map(eventRequest, EventEntity.class);
        eventEntity = eventRepository.save(eventEntity);
        return modelMapper.map(eventEntity, EventResponse.class);
    }

    @Override
    public EventResponse updateEvent(EventRequest eventRequest) {
        EventEntity eventEntity = modelMapper.map(eventRequest, EventEntity.class);
        EventEntity dbEntity = eventRepository.getEventEntityByEventId(eventRequest.getEventId());
        eventEntity.setId(dbEntity.getId());
        eventEntity = eventRepository.save(eventEntity);
        return modelMapper.map(eventEntity, EventResponse.class);

    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.getEventEntitiesBy().stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

    }

    @Override
    public EventResponse getEventById(String eventId) {
        EventEntity eventEntity = eventRepository.getEventEntityByEventId(eventId);
        return modelMapper.map(eventEntity, EventResponse.class);

    }

    @Override
    public void deleteEventByEventId(String eventId) {
        eventRepository.deleteEventEntityByEventId(eventId);

    }

    @Override
    public List<EventResponse> getEventsByCategoryId(String categoryId) {
        return eventRepository.findEventEntitiesByCategory_CategoryId(categoryId).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

    }

    @Override
    public List<EventResponse> getEventsByCategoryName(String categoryName) {
        return eventRepository.findEventEntitiesByCategory_Name(categoryName).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

    }

    @Override
    public List<EventResponse> getPopularEvents() {
        int numberOfVotesThresholdToBePopular = 5;
        return eventRepository.findEventEntitiesByVotesGreaterThan(numberOfVotesThresholdToBePopular).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> getUpcomingEvents() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        return eventRepository.findEventEntitiesByStartDateTimeAfter(date).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());
    }


}
