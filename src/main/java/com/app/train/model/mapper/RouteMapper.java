package com.app.train.model.mapper;

import com.app.train.model.dto.RouteDto;
import com.app.train.model.entity.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDto toDto(Route route);
}
