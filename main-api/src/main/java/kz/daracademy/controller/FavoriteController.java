package kz.daracademy.controller;

import kz.daracademy.feign.DataStoreFeign;

import kz.daracademy.model.Favorite;
import kz.daracademy.model.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private DataStoreFeign dataStoreFeign;


    @PostMapping
    public ResponseEntity<String> createFavorite(@RequestBody Favorite favorite) {
        UserDetailsModel principal = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        favorite.setUserId(principal.getUserId());
        return dataStoreFeign.createFavorite(favorite);
    }

    @GetMapping("/all")
    public List<Favorite> getAllFavorites() {
        return dataStoreFeign.getAllFavorites();
    }

    @GetMapping
    public List<Favorite> getFavoritesByUserId(@RequestParam String userId) {
        return dataStoreFeign.getFavoritesByUserId(userId);
    }

    @DeleteMapping
    public void deleteFavorite(@RequestParam String favoriteId) {
        dataStoreFeign.deleteFavorite(favoriteId);
    }
}
