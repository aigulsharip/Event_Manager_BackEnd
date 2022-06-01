package kz.daracademy.service.user;

import kz.daracademy.model.user.UserEntity;
import kz.daracademy.model.user.UserRequest;
import kz.daracademy.model.user.UserResponse;
import kz.daracademy.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {

        userRequest.setUserIdF(UUID.randomUUID().toString());
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponse.class);


    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        UserEntity dbEntity = userRepository.getUserEntityByUserId(userRequest.getUserId());
        userEntity.setId(dbEntity.getId());
        userEntity = userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getUserEntitiesBy().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());


    }

    @Override
    public UserResponse getUserById(String userId) {
        UserEntity userEntity = userRepository.getUserEntityByUserId(userId);
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public void deleteUserByUserId(String userId) {
        userRepository.deleteUserEntityByUserId(userId);
    }
}
