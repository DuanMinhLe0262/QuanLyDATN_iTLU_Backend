package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.GiangVienCreationRequest;
import com.example.backend_itlu.dto.request.GiangVienUpdateRequest;
import com.example.backend_itlu.dto.response.GiangVienResponse;
import com.example.backend_itlu.entity.GiangVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BoMonMapper.class, UserMapper.class})
public interface GiangVienMapper {

    GiangVien toGiangVien(GiangVienCreationRequest request);

    @Mapping(source = "boMon.id", target = "boMonId" )
    @Mapping(source = "user.id", target = "userId" )
    GiangVienResponse toGiangVienResponse(GiangVien giangVien);

    List<GiangVienResponse> toGiangVienResponseList(List<GiangVien> giangVienList);

    void updateGiangVienFromRequest(@MappingTarget GiangVien giangVien, GiangVienUpdateRequest request);
}
