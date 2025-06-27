package com.example.backend_itlu.entity;

import com.example.backend_itlu.enums.GioiTinh;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String maSinhVien;
    String hoDem;
    String ten;
    LocalDate ngaySinh;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    GioiTinh gioiTinh;

    String diaChi;

    String soDienThoai;

    @Column(name = "avatar_url")
    String avatarUrl;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    User user;

    @ManyToOne
    @JoinColumn(name = "lop_id")
    Lop lop;

    @ManyToOne
    @JoinColumn(name = "dot_id")
    DotDoAn dotDoAn;

    @OneToMany(mappedBy = "sinhVien")
    Set<SinhVienHuongDan> listGVHD = new HashSet<>();
}
