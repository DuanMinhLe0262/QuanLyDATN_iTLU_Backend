package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.DeTaiCreationRequest;
import com.example.backend_itlu.dto.request.DeTaiUpdateRequest;
import com.example.backend_itlu.dto.response.DeTaiResponse;
import com.example.backend_itlu.entity.DeTai;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SinhVienMapper.class, GiangVienMapper.class})
public interface DeTaiMapper {

    DeTai toDeTai(DeTaiCreationRequest request);

    DeTaiResponse toDeTaiResponse(DeTai deTai);

    List<DeTaiResponse> toDeTaiResponseList(List<DeTai> deTaiList);

    void updateDeTaiFromRequest(@MappingTarget DeTai deTai, DeTaiUpdateRequest request);

    DeTai idDeTaiToDeTai(String idDeTai);
}
