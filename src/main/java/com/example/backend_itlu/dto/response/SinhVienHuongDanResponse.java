package com.example.backend_itlu.dto.response;

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
public class SinhVienHuongDanResponse {


    String id;

    SinhVienResponse sinhVien;

    GiangVienResponse giangVien;

    VaiTroHuongDan vaiTroHuongDan;
}
