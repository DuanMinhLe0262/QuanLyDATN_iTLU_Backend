package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.UserCreationRequest;
import com.example.backend_itlu.dto.request.UserUpdateRequest;
import com.example.backend_itlu.dto.response.UserResponse;
import com.example.backend_itlu.entity.GiangVien;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.entity.User;
import com.example.backend_itlu.enums.Role;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.GiangVienMapper;
import com.example.backend_itlu.mapper.SinhVienMapper;
import com.example.backend_itlu.mapper.UserMapper;
import com.example.backend_itlu.repository.GiangVienRepository;
import com.example.backend_itlu.repository.SinhVienRepository;
import com.example.backend_itlu.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    SinhVienRepository sinhVienRepo;
    SinhVienMapper sinhVienMapper;
    GiangVienMapper giangVienMapper;
    GiangVienRepository giangVienRepo;



    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<Role> roles = new HashSet<>(user.getRoles());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepo.save(user));
    }


    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND, "Không tìm thấy người dùng với ID: " + userId));

        userMapper.updateUserFromRequest(user, request);
        return userMapper.toUserResponse(userRepo.save(user));
    }

    public void deleteUser(String userId) {
        boolean exists = userRepo.existsById(userId);
        if (!exists) {
            throw new AppException(ErrorCode.USER_NOT_FOUND, "Không thể xoá. Người dùng không tồn tại: " + userId);
        }
        userRepo.deleteById(userId);
    }

    public ResponseEntity<?> getMyInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (user.getRoles().contains(Role.SINHVIEN)) {
            SinhVien sv = sinhVienRepo.findByUserId(user.getId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            return ResponseEntity.ok(sinhVienMapper.toSinhVienResponse(sv));
        }

        if (user.getRoles().contains(Role.GIANGVIEN)) {
            GiangVien gv = giangVienRepo.findByUserId(user.getId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            return ResponseEntity.ok(giangVienMapper.toGiangVienResponse(gv));
        }

        throw new AppException(ErrorCode.UNAUTHORIZED_ACCESS, "Không xác định được vai trò người dùng");
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.toUserResponseList(users);
    }

//    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND, "Không tìm thấy người dùng với ID: " + userId));

        return userMapper.toUserResponse(user);
    }
}
