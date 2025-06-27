package com.example.backend_itlu.entity;

import com.example.backend_itlu.enums.VaiTroHuongDan;
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
public class SinhVienHuongDan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "giang_vien_id")
    GiangVien giangVien;

    @Enumerated(EnumType.STRING)
    @Column(name = "vai_tro_huong_dan")
    VaiTroHuongDan vaiTroHuongDan;
}
