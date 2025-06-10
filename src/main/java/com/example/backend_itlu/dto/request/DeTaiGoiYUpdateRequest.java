package com.example.backend_itlu.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeTaiGoiYUpdateRequest {

    @Column(nullable = false)
    String tenDeTai;

    @Column(length = 1000)
    String noiDung;

    String mucTieu;

    String yeuCau;

    String linhVuc;

    String boMonId;

}
