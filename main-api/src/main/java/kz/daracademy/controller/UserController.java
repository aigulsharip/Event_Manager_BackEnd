package kz.daracademy.controller;

import kz.daracademy.feign.CommentFeign;
import kz.daracademy.feign.DataStoreFeign;
import kz.daracademy.model.Category;
import kz.daracademy.model.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private DataStoreFeign dataStoreFeign;


    @PostMapping
    public UserDetailsModel createUser() {
        UserDetailsModel principal = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dataStoreFeign.createUser(principal);
        return principal;
    }

}
