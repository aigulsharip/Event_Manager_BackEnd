package kz.daracademy.service.favorite;

import kz.daracademy.model.favorite.FavoriteEntity;
import kz.daracademy.model.favorite.FavoriteRequest;
import kz.daracademy.model.favorite.FavoriteResponse;

import java.util.List;

public interface FavoriteService {

    FavoriteResponse createFavorite(FavoriteRequest favoriteRequest);

    List<FavoriteResponse> getAllFavorites();

    List<FavoriteResponse> getFavoritesByUserId(String userId);

    void deleteFavoriteByFavoriteId(String favoriteId);
}
