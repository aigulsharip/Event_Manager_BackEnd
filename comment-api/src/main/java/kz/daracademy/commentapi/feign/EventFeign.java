package kz.daracademy.commentapi.feign;

import com.fasterxml.jackson.databind.util.JSONPObject;
import kz.daracademy.commentapi.model.CommentResponse;
import kz.daracademy.commentapi.model.EventRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient("data-store-api")
public interface EventFeign {


    @GetMapping("/event")
    EventRequest getEventById(@RequestParam String eventId);

    @GetMapping("/event/check")
    String check();
}
