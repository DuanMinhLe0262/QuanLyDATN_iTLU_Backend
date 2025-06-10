package com.example.backend_itlu.entity;

import com.example.backend_itlu.enums.GioiTinh;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lop_id")
    private Lop lop;
}
