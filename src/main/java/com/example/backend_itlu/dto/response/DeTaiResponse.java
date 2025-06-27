package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.entity.GiangVien;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeTaiResponse {

    @NotBlank
    String id;

    @NotBlank
    String tenDeTai;

    String moTa;

    TrangThaiNopBai trangThai;

    @NotBlank
    SinhVien sinhVien;

    @NotBlank
    GiangVien giangVien;
}
