package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.enums.TrangThaiDotDoAn;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DotDoAnCreationRequest {

    String tenDot;
    String namHoc;
    LocalDate thoiGianBatDau;
    LocalDate thoiGianKetThuc;
    TrangThaiDotDoAn trangThaiDot;
}
