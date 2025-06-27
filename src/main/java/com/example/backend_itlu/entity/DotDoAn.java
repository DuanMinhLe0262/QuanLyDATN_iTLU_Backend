package com.example.backend_itlu.entity;


import com.example.backend_itlu.enums.Dot;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "ten_dot")
    Dot tenDot;

    @Column(nullable = false)
    String namHoc;

    @Column(nullable = false)
    LocalDate thoiGianBatDau;

    @Column(nullable = false)
    LocalDate thoiGianKetThuc;
}


