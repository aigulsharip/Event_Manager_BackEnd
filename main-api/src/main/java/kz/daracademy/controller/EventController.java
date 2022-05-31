package kz.daracademy.controller;

import kz.daracademy.feign.EventFeign;
import kz.daracademy.model.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    /*
    @GetMapping("/all")
    public List<EventResponse> getAllEvents() {
        return eventFeign.getAllEvents();
    }

     */

}
