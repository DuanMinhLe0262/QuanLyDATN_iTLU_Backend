package com.example.backend_itlu.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LopCreationRequest {
    String tenLop;
    String moTa;
    String khoaHoc;
    String nganhId;
}
