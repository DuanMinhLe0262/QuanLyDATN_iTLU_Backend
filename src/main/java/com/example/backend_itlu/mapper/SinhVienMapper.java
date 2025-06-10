package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.SinhVienCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienUpdateRequest;
import com.example.backend_itlu.dto.response.SinhVienResponse;
import com.example.backend_itlu.entity.SinhVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LopMapper.class, UserMapper.class})
public interface SinhVienMapper {

    SinhVien toSinhVien(SinhVienCreationRequest request);

    @Mapping(source = "lop.id", target = "lopId" )
    @Mapping(source = "user.id", target = "userId" )
    SinhVienResponse toSinhVienResponse(SinhVien sinhvien);

    List<SinhVienResponse> toSinhVienResponseList(List<SinhVien> sinhVienList);

    void updateSinhVienFromRequest(@MappingTarget SinhVien sinhVien, SinhVienUpdateRequest request);
}
