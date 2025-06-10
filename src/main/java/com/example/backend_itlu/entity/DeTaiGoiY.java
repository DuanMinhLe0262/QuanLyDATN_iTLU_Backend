package com.example.backend_itlu.entity;

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
public class DeTaiGoiY {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String tenDeTai;

    @Column(length = 1000)
    String noiDung;

    String mucTieu;

    String yeuCau;

    String linhVuc;

    @ManyToOne
    @JoinColumn(name = "bomon_id")
    BoMon bomon;

}
