package kz.daracademy.controller;

import kz.daracademy.model.favorite.FavoriteRequest;
import kz.daracademy.model.favorite.FavoriteResponse;
import kz.daracademy.service.favorite.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @PostMapping
    public ResponseEntity<String> createFavorite(@RequestBody FavoriteRequest favoriteRequest) {
        FavoriteResponse favoriteResponse = favoriteService.createFavorite(favoriteRequest);
        if (favoriteResponse == null) {
            return new ResponseEntity<>("Dont added", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public List<FavoriteResponse> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping
    public List<FavoriteResponse> getFavoritesByUserId(@RequestParam String userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }

    @DeleteMapping
    public void deleteFavorite(@RequestParam String favoriteId) {
        favoriteService.deleteFavoriteByFavoriteId(favoriteId);
    }
}
