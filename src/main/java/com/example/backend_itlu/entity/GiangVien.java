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

public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String maGiangVien;
    String hoDem;
    String ten;
    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    GioiTinh gioiTinh;
    LocalDate ngaySinh;
    String soDienThoai;
    @Column(name = "avatar_url")
    String avatarUrl;
    String hocVi;
    String hocHam;
    String chucVu;
    @ManyToOne
    @JoinColumn(name = "bomon_id")
    BoMon boMon;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
