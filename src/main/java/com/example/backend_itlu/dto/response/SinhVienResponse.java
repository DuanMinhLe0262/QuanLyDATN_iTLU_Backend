package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.enums.GioiTinh;
import com.example.backend_itlu.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienResponse {

    String id;

    String maSinhVien;

    String hoDem;

    String ten;

    LocalDate ngaySinh;

    GioiTinh gioiTinh;

    String diaChi;

    String soDienThoai;

    String userId;

    String lopId;

}
