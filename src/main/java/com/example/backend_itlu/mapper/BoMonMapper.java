package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.BoMonCreationRequest;
import com.example.backend_itlu.dto.request.BoMonUpdateRequest;
import com.example.backend_itlu.dto.response.BoMonResponse;
import com.example.backend_itlu.entity.BoMon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {KhoaMapper.class})
public interface BoMonMapper {

    BoMon toBoMon(BoMonCreationRequest request);

    @Mapping(source = "khoa", target = "khoa")
    BoMonResponse toBoMonResponse(BoMon boMon);

    List<BoMonResponse> toBoMonResponseList(List<BoMon> boMonList);

    void updateBoMonFromRequest(@MappingTarget BoMon boMon, BoMonUpdateRequest request);

    BoMon idBoMonToBoMon(String idBoMon);
}
