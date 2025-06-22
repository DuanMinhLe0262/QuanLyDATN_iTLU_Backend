package com.example.backend_itlu.dto.request;

import com.example.backend_itlu.enums.LoaiTaiLieu;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaiLieuUpdateRequest {

    private TrangThaiNopBai trangThai;
    private String nhanXet;
    private String giangVienId;
}

