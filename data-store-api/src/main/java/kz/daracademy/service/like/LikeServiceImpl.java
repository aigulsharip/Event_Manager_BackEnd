package kz.daracademy.service.like;

import kz.daracademy.model.event.EventEntity;
import kz.daracademy.model.like.LikeEntity;
import kz.daracademy.model.like.LikeRequest;
import kz.daracademy.model.like.LikeResponse;
import kz.daracademy.model.user.UserEntity;
import kz.daracademy.repository.EventRepository;
import kz.daracademy.repository.LikeRepository;
import kz.daracademy.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public LikeResponse createLike(LikeRequest likeRequest) {
        likeRequest.setLikeId(UUID.randomUUID().toString());

        LikeEntity likeEntity = modelMapper.map(likeRequest, LikeEntity.class);

        try {
            LikeEntity likeEntity1 = likeRepository.getLikeEntityByEventIdAndUserId(likeEntity.getEventId(),
                    likeEntity.getUserId());

            if (likeEntity1 == null) {
                EventEntity eventEntity = eventRepository.getEventEntityByEventId(likeEntity.getEventId());
                UserEntity userEntity = userRepository.getUserEntityByUserId(likeEntity.getUserId());
                if (eventEntity == null || userEntity == null) {
                    throw new Exception();
                }

                likeEntity = likeRepository.save(likeEntity); // like добавляется в таблицу +1 like

                List<LikeEntity> likes = likeRepository.getLikeEntityByEventId(likeEntity.getEventId());
                int countVotes = likes.toArray().length; // подтягивается кол-во лайков у евента
                eventEntity.setLikes(countVotes);

                eventRepository.save(eventEntity);

                return modelMapper.map(likeEntity, LikeResponse.class);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Don`t created like");
            return null;
        }
    }

    @Override
    public int getLikesByEventId(String eventId) {
        return likeRepository.getLikeEntityByEventId(eventId).size();
    }

    @Override
    public boolean getLikeByEventIdAndUserId(String eventId, String userId) {
        LikeEntity likeEntity = likeRepository.getLikeEntityByEventIdAndUserId(eventId, userId);
        //            return modelMapper.map(likeEntity, LikeResponse.class);
        return likeEntity != null;
    }

    @Override
    public void deleteLikeByEventIdAndUserId(String eventId, String userId) {
        likeRepository.deleteLikeEntityByEventIdAndUserId(eventId, userId); // удаляется лайк -1 like

        EventEntity dbEntity = eventRepository.getEventEntityByEventId(eventId);

        List<LikeEntity> likes = likeRepository.getLikeEntityByEventId(eventId);
        int countVotes = likes.toArray().length; // подтягивается кол-во лайков у евента
        dbEntity.setLikes(countVotes);

        eventRepository.save(dbEntity);
    }
}
