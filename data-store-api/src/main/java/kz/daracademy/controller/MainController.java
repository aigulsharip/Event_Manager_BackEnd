package kz.daracademy.controller;

import kz.daracademy.feign.UserFeign;
import kz.daracademy.model.user.UserDetailsModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/check")
    public String check() {
        return userFeign.check();
    }

    @GetMapping("/no-auth")
    public String check2() {
        return userFeign.check2();
    }


    @GetMapping("/user")
    public UserDetailsModel getUser() {
        return userFeign.getUser();
    }


}
