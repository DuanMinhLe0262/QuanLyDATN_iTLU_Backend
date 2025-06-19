package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.enums.GioiTinh;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiangVienResponse {

    String id;
    String maGiangVien;
    String hoDem;
    String ten;
    GioiTinh gioiTinh;
    LocalDate ngaySinh;
    String soDienThoai;
    String avatarUrl;
    String hocVi;
    String hocHam;
    String chucVu;
    BoMonResponse boMon;
    UserResponse user;
}

