package com.example.backend_itlu.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NganhUpdateRequest {
    String tenNganh;
    String moTa;
    String boMonId;
}
