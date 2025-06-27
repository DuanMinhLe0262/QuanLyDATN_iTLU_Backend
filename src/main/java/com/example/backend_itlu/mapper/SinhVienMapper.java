package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.ExcelSinhVienCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienUpdateRequest;
import com.example.backend_itlu.dto.response.SinhVienResponse;
import com.example.backend_itlu.entity.SinhVien;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LopMapper.class, UserMapper.class, DotDoAnMapper.class})
public interface SinhVienMapper {

    SinhVien toSinhVien(SinhVienCreationRequest request);

    SinhVien ExcelSinhVientoSinhVien(ExcelSinhVienCreationRequest request);

    SinhVienResponse toSinhVienResponse(SinhVien sinhvien);

    List<SinhVienResponse> toSinhVienResponseList(List<SinhVien> sinhVienList);

    void updateSinhVienFromRequest(@MappingTarget SinhVien sinhVien, SinhVienUpdateRequest request);
    SinhVien sinhVienIdtoSinhVien(String sinhVienId);
}
