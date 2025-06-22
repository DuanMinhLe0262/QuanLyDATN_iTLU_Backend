package com.example.backend_itlu.entity;

import com.example.backend_itlu.enums.LoaiTaiLieu;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TaiLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    String tenDeTai;

    String moTa;

    String fileUrl;

    LocalDateTime ngayNop;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    LoaiTaiLieu loai;

    Integer lanNop;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    TrangThaiNopBai trangThai;

    String nhanXet;

    @ManyToOne
    @JoinColumn(name = "nguoi_duyet_id")
    GiangVien giangVien;

    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    SinhVien sinhVien;

}

