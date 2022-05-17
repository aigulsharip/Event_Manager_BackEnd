package kz.daracademy.service.event;

import kz.daracademy.model.event.EventEntity;
import kz.daracademy.model.event.EventRequest;
import kz.daracademy.model.event.EventResponse;
import kz.daracademy.repository.CategoryRepository;
import kz.daracademy.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        return eventRepository.findEventEntitiesByCategory_CategoryName(categoryName).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

    }

    @Override
    public List<EventResponse> getPopularEvents() {
        // List of events with votes more than 50 and sorted asc by votes
        int numberOfVotesThresholdToBePopular = 50;
        return eventRepository.findEventEntitiesByVotesGreaterThanOrderByVotesAsc(numberOfVotesThresholdToBePopular).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> getUpcomingEvents() {
        //List of events from today and onward 2 weeks
        long millis = System.currentTimeMillis();
        Date todayDate = new Date(millis);
        Date endDate = Date.from(LocalDate.now().plusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return eventRepository.findEventEntitiesByStartDateTimeBetweenOrderByStartDateTime(todayDate, endDate).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());


    }

    @Override
    public List<EventResponse> getNewEvents() {
        //List of events that was posted 2 weeks before , sorted by posted date
        Date postedDate2weeksBefore= Date.from(LocalDate.now().minusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(postedDate2weeksBefore);
        return eventRepository.findEventEntitiesByPostedDateAfterOrderByPostedDate(postedDate2weeksBefore).stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());


    }


}
