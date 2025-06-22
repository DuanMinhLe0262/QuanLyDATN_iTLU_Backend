package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.NganhCreationRequest;
import com.example.backend_itlu.dto.request.NganhUpdateRequest;
import com.example.backend_itlu.dto.response.NganhResponse;
import com.example.backend_itlu.entity.Nganh;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.xml.transform.Source;
import java.util.List;

@Mapper(componentModel = "spring", uses = {BoMonMapper.class})
public interface NganhMapper {

    Nganh toNganh(NganhCreationRequest request);

    NganhResponse toNganhResponse(Nganh Nganh);

    List<NganhResponse> toNganhResponseList(List<Nganh> NganhList);

    void updateNganhFromRequest(@MappingTarget Nganh Nganh, NganhUpdateRequest request);

    Nganh idNganhToNganh(String idNganh);
}
