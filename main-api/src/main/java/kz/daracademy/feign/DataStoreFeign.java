package kz.daracademy.feign;

import kz.daracademy.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("data-store-api")
public interface DataStoreFeign {

    @PostMapping("/user")
    void createUser(UserDetailsModel userDetailModel);

    //---------------------------------------------------
    @GetMapping("/category/all")
    List<Category> getAllCategorories();

    @GetMapping("/category")
    Category getCategoryById(@RequestParam String categoryId);

    @PostMapping("/category")
    Category createCategory(@RequestBody Category category);

    @PutMapping("/category")
    Category updateCategory(@RequestBody Category category, @RequestParam String categoryId);

    @DeleteMapping("/category")
    void deleteCategory(@RequestParam String categoryId);

    //-------------------------------------------------------------

    @GetMapping("/event/all")
    List<Event> getAllEvents();

    @GetMapping("/event")
    Event getEventById(@RequestParam String eventId);

    @PostMapping("/event")
    Event createEvent(@RequestBody Event category);

    @PutMapping("/event")
    Event updateEvent(@RequestBody Event category, @RequestParam String eventId);

    @DeleteMapping("/event")
    void deleteEvent(@RequestParam String eventId);

    @GetMapping("/event/user")
    List<Event> getEventsByUserId(@RequestParam String userId);

    @GetMapping("/filter")
    List<Event> filterEventByCategory(@RequestParam String categoryId);

    @GetMapping("/filter/categoryName")
    List<Event> filterEventsByCategoryName(@RequestParam String categoryName);

    @GetMapping("/section")
    List<Event> getSections (@RequestParam String sectionName);

    //-------------------------------------------------------------
    @PostMapping("/favorite")
    ResponseEntity<String> createFavorite(@RequestBody Favorite favorite);

    @GetMapping("/favorite/all")
    List<Favorite> getAllFavorites();

    @GetMapping("/favorite")
    List<Favorite> getFavoritesByUserId(@RequestParam String userId);

    @DeleteMapping("/favorite")
    void deleteFavorite(@RequestParam String favoriteId);

    //-------------------------------------------------------------

    @PostMapping("/like")
    ResponseEntity<String> createLike(@RequestBody Like like);

    @GetMapping("/like/all")
    int getLikesByEventId(@RequestParam String eventId);

    @GetMapping("/like")
    boolean getLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId);


    @DeleteMapping("/like")
    void deleteLikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId);

    //-------------------------------------------------------------


    @PostMapping("/dislike")
    ResponseEntity<String> createDislike(@RequestBody Dislike like);

    @GetMapping("/dislike/all")
    int getDislikesByEventId(@RequestParam String eventId);

    @GetMapping("/dislike")
    boolean getDislikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId);


    @DeleteMapping("/dislike")
    void deleteDislikeByEventIdAndUserId(@RequestParam String eventId, @RequestParam String userId);

}
