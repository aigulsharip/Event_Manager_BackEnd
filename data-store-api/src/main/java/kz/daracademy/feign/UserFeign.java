package kz.daracademy.feign;

import kz.daracademy.model.event.EventResponse;
import kz.daracademy.model.user.UserDetailsModel;
import kz.daracademy.model.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient("main-api")
public interface UserFeign {
    @GetMapping("/main/check")
    String check();

    @GetMapping("/main/no-auth")
    String check2();

    @GetMapping("/main/user")
    UserDetailsModel getUser();


}

