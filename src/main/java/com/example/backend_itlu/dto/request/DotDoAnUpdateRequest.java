package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.enums.Dot;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DotDoAnUpdateRequest {

    Dot tenDot;
    String namHoc;
    LocalDate thoiGianBatDau;
    LocalDate thoiGianKetThuc;
}
