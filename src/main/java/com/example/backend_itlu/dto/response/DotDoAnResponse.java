package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.enums.TrangThaiDotDoAn;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DotDoAnResponse {

    String id;
    String tenDot;
    String namHoc;
    LocalDate thoiGianBatDau;
    LocalDate thoiGianKetThuc;
    TrangThaiDotDoAn trangThaiDot;
    LocalDateTime thoiGianTao;
}
