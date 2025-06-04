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

public class SinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String maSinhVien;
    String hoTen;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lop_id")
    private Lop lop;
}
