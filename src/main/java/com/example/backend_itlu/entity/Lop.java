package com.example.backend_itlu.entity;

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

public class Lop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String tenLop;
    String moTa;
    String khoaHoc;

    @ManyToOne
    @JoinColumn(name = "nganh_id")
    Nganh nganh;
}
