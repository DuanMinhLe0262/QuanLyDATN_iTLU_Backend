package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.LopCreationRequest;
import com.example.backend_itlu.dto.request.LopUpdateRequest;
import com.example.backend_itlu.dto.response.LopResponse;
import com.example.backend_itlu.entity.Lop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.xml.transform.Source;
import java.util.List;

@Mapper(componentModel = "spring", uses = {NganhMapper.class})
public interface LopMapper {

    Lop toLop(LopCreationRequest request);

    @Mapping(source = "nganh", target = "nganh")
    LopResponse toLopResponse(Lop Lop);

    List<LopResponse> toLopResponseList(List<Lop> LopList);

    void updateLopFromRequest(@MappingTarget Lop Lop, LopUpdateRequest request);

    Lop idLopToLop( String idLop);
}
