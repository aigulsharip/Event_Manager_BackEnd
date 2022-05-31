package kz.daracademy.feign;

import kz.daracademy.model.EventResponse;
import kz.daracademy.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("data-store-api")
public interface EventFeign {

    @GetMapping("/event/check")
    String check();

    @GetMapping("/event")
    EventResponse getEventById(@RequestParam String eventId);

    @GetMapping("/event/all")
    List<EventResponse> getAllEvents();


}
