package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.enums.Dot;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DotDoAnResponse {

    String id;
    Dot tenDot;
    String namHoc;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate thoiGianBatDau;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate thoiGianKetThuc;
}
