package kz.daracademy.service.dislike;

import kz.daracademy.model.dislike.DislikeEntity;
import kz.daracademy.model.dislike.DislikeRequest;
import kz.daracademy.model.dislike.DislikeResponse;
import kz.daracademy.model.event.EventEntity;
import kz.daracademy.repository.DislikeRepository;
import kz.daracademy.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DislikeServiceImpl implements DislikeService {
    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private EventRepository eventRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public DislikeResponse createDislike(DislikeRequest dislikeRequest) {
        dislikeRequest.setDislikeId(UUID.randomUUID().toString());

        DislikeEntity dislikeEntity = modelMapper.map(dislikeRequest, DislikeEntity.class);
        try {
            DislikeEntity dislikeEntity1 = dislikeRepository.getDislikeEntityByEventIdAndUserId(dislikeEntity.getEventId(),
                    dislikeEntity.getUserId());

            if (dislikeEntity1 == null) {
                dislikeEntity = dislikeRepository.save(dislikeEntity); // dislike добавляется в таблицу +1 dislike

                EventEntity dbEntity = eventRepository.getEventEntityByEventId(dislikeEntity.getEventId());

                List<DislikeEntity> dislikes = dislikeRepository.getDislikeEntityByEventId(dislikeEntity.getEventId());
                int countVotes = dislikes.toArray().length; // подтягивается кол-во дизлайков у евента
                dbEntity.setVotes(countVotes);

                eventRepository.save(dbEntity);

                return modelMapper.map(dislikeEntity, DislikeResponse.class);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Don`t created dislike");
            return null;
        }
    }

    @Override
    public int getDislikesByEventId(String eventId) {
        return dislikeRepository.getDislikeEntityByEventId(eventId).size();
    }

    @Override
    public boolean getDislikeByEventIdAndUserId(String eventId, String userId) {
        DislikeEntity dislikeEntity = dislikeRepository.getDislikeEntityByEventIdAndUserId(eventId, userId);
        //            return modelMapper.map(dislikeEntity, DislikeResponse.class);
        return dislikeEntity != null;
    }

    @Override
    public void deleteDislikeByEventIdAndUserId(String eventId, String userId) {
        dislikeRepository.deleteDislikeEntityByEventIdAndUserId(eventId, userId); // удаляется дизлайк -1 dislike

        EventEntity dbEntity = eventRepository.getEventEntityByEventId(eventId);

        List<DislikeEntity> dislies = dislikeRepository.getDislikeEntityByEventId(eventId);
        int countVotes = dislies.toArray().length; // подтягивается кол-во дизлайков у евента
        dbEntity.setVotes(countVotes);

        eventRepository.save(dbEntity);
    }
}
