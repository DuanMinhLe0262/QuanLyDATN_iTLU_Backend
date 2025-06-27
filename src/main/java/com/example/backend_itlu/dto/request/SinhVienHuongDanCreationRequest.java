package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.entity.GiangVien;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.enums.Dot;
import com.example.backend_itlu.enums.VaiTroHuongDan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienHuongDanCreationRequest {

    @NotBlank
    String maSinhVien;

    @NotBlank
    String maGiangVien;

    @NotNull
    VaiTroHuongDan vaiTroHuongDan;
}
