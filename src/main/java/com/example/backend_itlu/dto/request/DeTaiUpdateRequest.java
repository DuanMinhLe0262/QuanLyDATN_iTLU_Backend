package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.enums.TrangThaiNopBai;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeTaiUpdateRequest {

    @NotBlank
    String id;

    @NotBlank
    String tenDeTai;

    String moTa;

    TrangThaiNopBai trangThai;

    @NotBlank
    String maSinhVien;

    @NotBlank
    String maGiangVien;
}
