package com.example.backend_itlu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LopResponse {
    String id;
    String tenLop;
    String khoaHoc;
    NganhResponse nganh;
}
