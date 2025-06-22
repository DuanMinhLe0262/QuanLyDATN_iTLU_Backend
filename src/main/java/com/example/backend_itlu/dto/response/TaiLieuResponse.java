package com.example.backend_itlu.dto.response;

import com.example.backend_itlu.enums.LoaiTaiLieu;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaiLieuResponse {

    String id;
    String tenDeTai;
    String moTa;
    String fileUrl;
    LocalDateTime ngayNop;
    LoaiTaiLieu loai;
    Integer lanNop;
    TrangThaiNopBai trangThai;
    String nhanXet;
    GiangVienResponse giangVien;
    SinhVienResponse sinhVien;
}

