package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.enums.GioiTinh;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhoaResponse {

    String id;
    String tenKhoa;
    String moTa;
}
