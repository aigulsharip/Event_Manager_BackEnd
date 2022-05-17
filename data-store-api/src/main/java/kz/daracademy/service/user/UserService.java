package kz.daracademy.service.user;

import kz.daracademy.model.user.UserRequest;
import kz.daracademy.model.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(String userId);

    void deleteUserByUserId(String userId);


}
