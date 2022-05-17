package kz.daracademy.controller;

import kz.daracademy.model.user.UserRequest;
import kz.daracademy.model.user.UserResponse;
import kz.daracademy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @RequestParam String userId) {
        userRequest.setUserId(userId);
        return userService.updateUser(userRequest);
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping()
    public UserResponse getUserById(@RequestParam String userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping
    public void deleteEvent(@RequestParam String userId) {
        userService.deleteUserByUserId(userId);
    }


}
