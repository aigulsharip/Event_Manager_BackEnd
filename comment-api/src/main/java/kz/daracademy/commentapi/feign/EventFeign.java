package kz.daracademy.commentapi.feign;

import kz.daracademy.commentapi.model.event.EventResponse;
import kz.daracademy.commentapi.model.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("data-store-api")
public interface EventFeign {

    //@GetMapping("/event")
    //EventRequest getEventById(@RequestParam String eventId);

    @GetMapping("/event/check")
    String check();

    @GetMapping("/event")
    EventResponse getEventById(@RequestParam String eventId);

    @GetMapping("/user")
    UserResponse getUserById(@RequestParam String userId);

}
