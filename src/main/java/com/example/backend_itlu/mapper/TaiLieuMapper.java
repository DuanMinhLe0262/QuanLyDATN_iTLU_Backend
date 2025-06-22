package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.GiangVienCreationRequest;
import com.example.backend_itlu.dto.request.GiangVienUpdateRequest;
import com.example.backend_itlu.dto.request.TaiLieuCreationRequest;
import com.example.backend_itlu.dto.response.DotDoAnResponse;
import com.example.backend_itlu.dto.response.GiangVienResponse;
import com.example.backend_itlu.dto.response.TaiLieuResponse;
import com.example.backend_itlu.entity.GiangVien;
import com.example.backend_itlu.entity.TaiLieu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GiangVienMapper.class, SinhVienMapper.class})
public interface TaiLieuMapper {

    TaiLieu toTaiLieu(TaiLieuCreationRequest request);

    TaiLieuResponse toTaiLieuResponse(TaiLieu taiLieu);

    List<TaiLieuResponse> toTaiLieuResponseList(List<TaiLieu> taiLieuList);
}
