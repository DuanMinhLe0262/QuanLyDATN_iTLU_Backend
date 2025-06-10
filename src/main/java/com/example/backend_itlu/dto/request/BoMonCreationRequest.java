package com.example.backend_itlu.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoMonCreationRequest {
    String tenBoMon;
    String moTa;
    String khoaId;
}
