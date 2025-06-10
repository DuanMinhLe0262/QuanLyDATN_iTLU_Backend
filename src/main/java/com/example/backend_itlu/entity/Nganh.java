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

public class Nganh {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String tenNganh;
    String moTa;

    @ManyToOne
    @JoinColumn(name = "bomon_id")
    BoMon boMon;
}
