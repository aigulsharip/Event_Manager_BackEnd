package kz.daracademy.controller;

import kz.daracademy.model.UserDetailsModel;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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
