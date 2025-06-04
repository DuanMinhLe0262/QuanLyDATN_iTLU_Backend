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

public class GiangVien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String maGiangVien;
    String hoTen;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bomon_id")
    private BoMon boMon;
}
