package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.KhoaCreationRequest;
import com.example.backend_itlu.dto.request.KhoaUpdateRequest;
import com.example.backend_itlu.dto.response.BoMonResponse;
import com.example.backend_itlu.dto.response.KhoaResponse;
import com.example.backend_itlu.entity.Khoa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KhoaMapper {

    Khoa toKhoa(KhoaCreationRequest request);

    KhoaResponse toKhoaResponse(Khoa khoa);

    List<KhoaResponse> toKhoaResponseList(List<Khoa> khoaList);

    void updateKhoaFromRequest(@MappingTarget Khoa khoa, KhoaUpdateRequest request);

    Khoa idKhoaToKhoa(String idKhoa);

//    String KhoaToKhoaId(Khoa khoa);
}
