package com.example.backend_itlu.entity;


import com.example.backend_itlu.enums.GioiTinh;
import com.example.backend_itlu.enums.TrangThaiDotDoAn;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DotDoAn {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String tenDot;

    @Column(nullable = false)
    String namHoc;

    @Column(nullable = false)
    LocalDate thoiGianBatDau;

    @Column(nullable = false)
    LocalDate thoiGianKetThuc;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_dot", nullable = false)
    TrangThaiDotDoAn trangThaiDot;

    @Column(nullable = false, updatable = false)
    LocalDateTime thoiGianTao;

    @PrePersist
    public void prePersist() {
        this.thoiGianTao = LocalDateTime.now();
    }
}


