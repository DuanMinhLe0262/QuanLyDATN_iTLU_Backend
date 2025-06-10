package com.example.backend_itlu.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Khoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String tenKhoa;
    String moTa;

    @OneToMany(mappedBy = "khoa")
    private List<BoMon> boMonList;
}


