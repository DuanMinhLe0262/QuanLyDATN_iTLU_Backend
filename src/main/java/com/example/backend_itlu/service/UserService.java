package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.UserCreationRequest;
import com.example.backend_itlu.dto.request.UserUpdateRequest;
import com.example.backend_itlu.dto.response.UserResponse;
import com.example.backend_itlu.entity.User;
import com.example.backend_itlu.enums.Role;
import com.example.backend_itlu.mapper.UserMapper;
import com.example.backend_itlu.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {


        User user = userMapper. toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roles.addAll(user.getRoles());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepo.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userMapper.updateUserFromRequest(user, request);
        return userMapper.toUserResponse(userRepo.save(user));
    }

    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with name: " + email));
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {

        log.info("In method getAllUsers");
        List<User> users = userRepo.findAll();
        return userMapper.toUserResponseList(users);
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return userMapper.toUserResponse(user);
    }
}
