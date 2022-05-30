package kz.daracademy.controller;

import kz.daracademy.model.UserDetailsModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/check")
    public String healthCheck() {
        return "Hello! It's work!";
    }

    @GetMapping("/no-auth")
    public String publicEndpoint() {
        return "This method is public";
    }

    @GetMapping("/user")
    public UserDetailsModel getUserByJWT() {
        UserDetailsModel principal = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }











}
