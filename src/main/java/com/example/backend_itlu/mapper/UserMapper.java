package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.UserCreationRequest;
import com.example.backend_itlu.dto.request.UserUpdateRequest;
import com.example.backend_itlu.dto.response.UserResponse;
import com.example.backend_itlu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> userList);

    void updateUserFromRequest(@MappingTarget User user, UserUpdateRequest request);
}
