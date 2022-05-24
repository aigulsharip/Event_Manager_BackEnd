package kz.daracademy.service.event;

import kz.daracademy.model.category.CategoryEntity;
import kz.daracademy.model.event.EventEntity;
import kz.daracademy.model.event.EventNotificationInfo;
import kz.daracademy.model.event.EventRequest;
import kz.daracademy.model.event.EventResponse;
import kz.daracademy.model.img.ImgResponse;
import kz.daracademy.model.user.UserEntity;
import kz.daracademy.repository.CategoryRepository;
import kz.daracademy.repository.EventRepository;
import kz.daracademy.repository.UserRepository;
import kz.daracademy.service.img.ImgServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImgServiceImpl imgService;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        String eventId = UUID.randomUUID().toString();
        eventRequest.setEventId(eventId);
        eventRequest.setVotes(0);

        UserEntity userEntity = userRepository.getUserEntityByUserId(eventRequest.getUser().getUserId());

        CategoryEntity categoryEntity = categoryRepository.getCategoryEntityByCategoryId(eventRequest.getCategory().getCategoryId());

        eventRequest.setUser(userEntity);
        eventRequest.setCategory(categoryEntity);

        List<ImgResponse> imgResponses = new ArrayList<>();
        if (eventRequest.getPictures() != null) {
            eventRequest.getPictures().forEach(i -> {
                try {
                    imgResponses.add(imgService.saveImg(i.getFile(), i.getEventId(), i.isMainPhoto()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            eventRequest.setPictures(null);
        }

        EventEntity eventEntity = modelMapper.map(eventRequest, EventEntity.class);
        eventEntity = eventRepository.save(eventEntity);

        EventResponse eventResponse = modelMapper.map(eventEntity, EventResponse.class);

        eventResponse.setPictures(imgResponses);
        return eventResponse;
    }

    @Override
    public EventResponse updateEvent(EventRequest eventRequest) {
        EventEntity eventEntity = modelMapper.map(eventRequest, EventEntity.class);
        EventEntity dbEntity = eventRepository.getEventEntityByEventId(eventRequest.getEventId());
        eventEntity.setId(dbEntity.getId());

        List<ImgResponse> imgResponses = new ArrayList<>();
        if (eventRequest.getPictures() != null) {
            eventRequest.getPictures().forEach(i -> {
                try {
                    imgService.deleteImgByEventId(i.getEventId());
                    imgResponses.add(imgService.saveImg(i.getFile(), i.getEventId(), i.isMainPhoto()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        eventRequest.setPictures(null);
        eventEntity = eventRepository.save(eventEntity);

        EventResponse eventResponse = modelMapper.map(eventEntity, EventResponse.class);

        eventResponse.setPictures(imgResponses);
        return eventResponse;
    }

    @Override
    public List<EventResponse> getAllEvents() {
        List<EventResponse> eventResponses = eventRepository.getEventEntitiesBy().stream()
                .map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

        eventResponses.forEach(e -> {
            List<ImgResponse> imgResponses = new ArrayList<>();
            imgResponses.add(imgService.getImgByEventIdAndMain(e.getEventId(), true));
            e.setPictures(imgResponses);
        });

        return eventResponses;
    }

    @Override
    public EventResponse getEventById(String eventId) {
        EventEntity eventEntity = eventRepository.getEventEntityByEventId(eventId);
        EventResponse eventResponse = modelMapper.map(eventEntity, EventResponse.class);
        eventResponse.setPictures(imgService.getImgsByEventId(eventId));
        return eventResponse;

    }

    @Override
    public void deleteEventByEventId(String eventId) {
        eventRepository.deleteEventEntityByEventId(eventId);
        imgService.deleteImgByEventId(eventId);
    }

    @Override
    public List<EventResponse> getEventsByCategoryId(String categoryId) {
        List<EventResponse> eventResponses = eventRepository.findEventEntitiesByCategory_CategoryId(categoryId).stream()
                .map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

        eventResponses.forEach(e -> {
            List<ImgResponse> imgResponses = new ArrayList<>();
            imgResponses.add(imgService.getImgByEventIdAndMain(e.getEventId(), true));
            e.setPictures(imgResponses);
        });

        return eventResponses;
    }

    @Override
    public List<EventResponse> getEventsByCategoryName(String categoryName) {

        List<EventResponse> eventResponses = eventRepository.findEventEntitiesByCategory_CategoryName(categoryName).stream()
                .map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

        eventResponses.forEach(e -> {
            List<ImgResponse> imgResponses = new ArrayList<>();
            imgResponses.add(imgService.getImgByEventIdAndMain(e.getEventId(), true));
            e.setPictures(imgResponses);
        });

        return eventResponses;
    }

    @Override
    public List<EventResponse> getPopularEvents() {
        // List of events with votes more than 50 and sorted asc by votes
        int numberOfVotesThresholdToBePopular = 50;

        List<EventResponse> eventResponses = eventRepository.findEventEntitiesByVotesGreaterThanOrderByVotesAsc(numberOfVotesThresholdToBePopular)
                .stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

        eventResponses.forEach(e -> {
            List<ImgResponse> imgResponses = new ArrayList<>();
            imgResponses.add(imgService.getImgByEventIdAndMain(e.getEventId(), true));
            e.setPictures(imgResponses);
        });

        return eventResponses;
    }

    @Override
    public List<EventResponse> getUpcomingEvents() {
        //List of events from today and onward 2 weeks
        long millis = System.currentTimeMillis();
        Date todayDate = new Date(millis);
        Date endDate = Date.from(LocalDate.now().plusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<EventResponse> eventResponses = eventRepository.findEventEntitiesByStartDateTimeBetweenOrderByStartDateTime(todayDate, endDate)
                .stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

        eventResponses.forEach(e -> {
            List<ImgResponse> imgResponses = new ArrayList<>();
            imgResponses.add(imgService.getImgByEventIdAndMain(e.getEventId(), true));
            e.setPictures(imgResponses);
        });
        return eventResponses;
    }

    @Override
    public List<EventResponse> getNewEvents() {
        //List of events that was posted 2 weeks before , sorted by posted date
        Date postedDate2weeksBefore = Date.from(LocalDate.now().minusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(postedDate2weeksBefore);

        List<EventResponse> eventResponses = eventRepository.findEventEntitiesByPostedDateAfterOrderByPostedDate(postedDate2weeksBefore)
                .stream().map(event -> modelMapper.map(event, EventResponse.class)).collect(Collectors.toList());

        eventResponses.forEach(e -> {
            List<ImgResponse> imgResponses = new ArrayList<>();
            imgResponses.add(imgService.getImgByEventIdAndMain(e.getEventId(), true));
            e.setPictures(imgResponses);
        });
        return eventResponses;
    }

    @Override
    public EventNotificationInfo prepareEventInfoForNotification(String eventId) {
        EventNotificationInfo eventNotificationInfo = new EventNotificationInfo();
        EventResponse event = getEventById(eventId);
        eventNotificationInfo.setTitle(event.getTitle());
        eventNotificationInfo.setName(event.getUser().getFullName());
        eventNotificationInfo.setEmail(event.getUser().getEmail());
        eventNotificationInfo.setPostedDate(event.getPostedDate());
        eventNotificationInfo.setStartDateTime(event.getStartDateTime());

        return eventNotificationInfo;
    }


}
