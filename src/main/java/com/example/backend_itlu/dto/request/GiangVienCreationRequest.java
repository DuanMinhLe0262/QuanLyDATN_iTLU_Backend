package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.entity.BoMon;
import com.example.backend_itlu.entity.User;
import com.example.backend_itlu.enums.GioiTinh;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiangVienCreationRequest {

    String maGiangVien;
    String hoDem;
    String ten;
    GioiTinh gioiTinh;
    LocalDate ngaySinh;

    @Pattern(regexp = "^(\\+84|0)(3|5|7|8|9)[0-9]{8}$", message = "Số điện thoại không hợp lệ")
    String soDienThoai;
    String avatarUrl;
    String hocVi;
    String hocHam;
    String chucVu;

    String boMonId;
    String userId;
}

