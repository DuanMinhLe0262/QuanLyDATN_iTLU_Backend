package com.example.backend_itlu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NganhResponse {

    String id;
    String tenNganh;
    String moTa;
    String boMonId;
}
