package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    Set<Role> roles;
}
