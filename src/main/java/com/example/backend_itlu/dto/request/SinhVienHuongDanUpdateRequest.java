package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.enums.VaiTroHuongDan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienHuongDanUpdateRequest {

    @NotBlank
    String maSinhVien;

    @NotBlank
    String maGiangVien;

    @NotNull
    VaiTroHuongDan vaiTroHuongDan;
}
