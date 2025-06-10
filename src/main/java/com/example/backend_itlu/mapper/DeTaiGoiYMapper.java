package com.example.backend_itlu.mapper;

import com.example.backend_itlu.dto.request.DeTaiGoiYCreationRequest;
import com.example.backend_itlu.dto.request.DeTaiGoiYUpdateRequest;
import com.example.backend_itlu.dto.response.DeTaiGoiYResponse;
import com.example.backend_itlu.entity.DeTaiGoiY;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BoMonMapper.class})
public interface DeTaiGoiYMapper {

    DeTaiGoiY toDeTaiGoiY(DeTaiGoiYCreationRequest request);

    @Mapping(source = "boMon.id", target = "boMonId")
    DeTaiGoiYResponse toDeTaiGoiYResponse(DeTaiGoiY deTaiGoiY);

    List<DeTaiGoiYResponse> toDeTaiGoiYResponseList(List<DeTaiGoiY> deTaiGoiYList);

    void updateDeTaiGoiYFromRequest(@MappingTarget DeTaiGoiY deTaiGoiY, DeTaiGoiYUpdateRequest request);

    DeTaiGoiY idDeTaiGoiYToDeTaiGoiY(String idDeTaiGoiY);
}
