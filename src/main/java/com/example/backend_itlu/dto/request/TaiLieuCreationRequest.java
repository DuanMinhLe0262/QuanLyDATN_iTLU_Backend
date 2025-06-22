package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.entity.DotDoAn;
import com.example.backend_itlu.entity.GiangVien;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.enums.GioiTinh;
import com.example.backend_itlu.enums.LoaiTaiLieu;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaiLieuCreationRequest {

    String tenDeTai;
    String moTa;
    String fileUrl;
    LocalDateTime ngayNop;
    LoaiTaiLieu loai;
    Integer lanNop;
    TrangThaiNopBai trangThai;
    String nhanXet;
    String giangVienId;
    String sinhVienId;
}

