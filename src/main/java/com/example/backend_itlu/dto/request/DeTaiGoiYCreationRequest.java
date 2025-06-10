package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.entity.BoMon;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeTaiGoiYCreationRequest {

    @Column(nullable = false)
    String tenDeTai;

    @Column(length = 1000)
    String noiDung;

    String mucTieu;

    String yeuCau;

    String linhVuc;

    String boMonId;

}
