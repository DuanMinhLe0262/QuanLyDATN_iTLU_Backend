package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.SinhVienHuongDanResponse;
import com.example.backend_itlu.entity.SinhVienHuongDan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SinhVienMapper.class, GiangVienMapper.class})
public interface SinhVienHuongDanMapper {

    SinhVienHuongDan toSinhVienHuongDan(SinhVienHuongDanCreationRequest request);

    SinhVienHuongDan ExcelSinhVienHuongDantoSinhVienHuongDan(ExcelSinhVienCreationRequest request);

    SinhVienHuongDanResponse toSinhVienHuongDanResponse(SinhVienHuongDan sinhVienHuongDan);

    List<SinhVienHuongDanResponse> toSinhVienHuongDanResponseList(List<SinhVienHuongDan> sinhVienHuongDanList);

    void updateSinhVienHuongDanFromRequest(@MappingTarget SinhVienHuongDan sinhVienHuongDan, SinhVienHuongDanUpdateRequest request);
}
