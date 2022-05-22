package kz.daracademy.service.favorite;

import kz.daracademy.model.favorite.FavoriteEntity;
import kz.daracademy.model.favorite.FavoriteRequest;
import kz.daracademy.model.favorite.FavoriteResponse;
import kz.daracademy.repository.FavoriteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public FavoriteResponse createFavorite(FavoriteRequest favoriteRequest) {
        favoriteRequest.setFavoriteId(UUID.randomUUID().toString());

        FavoriteEntity favoriteEntity = modelMapper.map(favoriteRequest, FavoriteEntity.class);

        try {
            FavoriteEntity favoriteEntity1 = favoriteRepository.getFavoriteEntityByUserIdAndEventId(favoriteEntity.getUserId(),
                    favoriteEntity.getEventId());
            System.out.println(favoriteEntity1);

            if (favoriteEntity1 == null) {
                favoriteEntity = favoriteRepository.save(favoriteEntity);
                return modelMapper.map(favoriteEntity, FavoriteResponse.class);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Don`t created");
            return null;
        }
    }

    @Override
    public List<FavoriteResponse> getAllFavorites() {
        return favoriteRepository.getFavoriteEntitiesBy().stream().map(f -> modelMapper.map(f, FavoriteResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<FavoriteResponse> getFavoritesByUserId(String userId) {
        return favoriteRepository.getFavoriteEntitiesByUserId(userId).stream().map(f -> modelMapper.map(f, FavoriteResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteFavoriteByFavoriteId(String favoriteId) {
        favoriteRepository.deleteFavoriteEntitiesByFavoriteId(favoriteId);
    }
}
