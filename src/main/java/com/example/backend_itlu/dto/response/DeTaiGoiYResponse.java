package com.example.backend_itlu.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeTaiGoiYResponse {

    String id;
    String tenDeTai;
    String noiDung;
    String mucTieu;
    String yeuCau;
    String linhVuc;
    BoMonResponse boMon;
}
