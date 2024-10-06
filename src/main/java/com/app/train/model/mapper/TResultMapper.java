package com.app.train.model.mapper;

import com.app.train.model.dto.TResultDto;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.Travel;
import com.app.train.service.TravelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TravelService.class)
public interface TResultMapper {
    @Mapping(source = "travelId", target = "travel")
    TravelResult toEntity(TResultDto dto);
}
