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

public class BoMon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String tenBoMon;
    String moTa;

    @ManyToOne
    @JoinColumn(name = "khoa_id")
    Khoa khoa;
}
