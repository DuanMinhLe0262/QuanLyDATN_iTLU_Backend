package com.example.backend_itlu.entity;

import com.example.backend_itlu.enums.TrangThaiNopBai;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeTai {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String tenDeTai;
    String moTa;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    TrangThaiNopBai trangThai;

    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "giang_vien_id")
    GiangVien giangVien;
}
