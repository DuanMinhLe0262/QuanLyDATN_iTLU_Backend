package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.DotDoAnCreationRequest;
import com.example.backend_itlu.dto.request.DotDoAnUpdateRequest;
import com.example.backend_itlu.dto.response.DotDoAnResponse;
import com.example.backend_itlu.entity.DotDoAn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DotDoAnMapper {

    DotDoAn toDotDoAn(DotDoAnCreationRequest request);

    DotDoAnResponse toDotDoAnResponse(DotDoAn dotDoAn);

    List<DotDoAnResponse> toDotDoAnResponseList(List<DotDoAn> dotDoAnList);

    void updateDotDoAnFromRequest(@MappingTarget DotDoAn dotDoAn, DotDoAnUpdateRequest request);
}
