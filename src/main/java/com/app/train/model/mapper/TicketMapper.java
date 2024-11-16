package com.app.train.model.mapper;

import com.app.train.model.dto.TicketRequest;
import com.app.train.model.entity.BaseTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "travel", source = "travelResult.travel")
    @Mapping(target = "boardingStationIndex", source = "travelResult.startId")
    @Mapping(target = "exitStationIndex", source = "travelResult.endId")
    @Mapping(target = "seatNumber", source = "seatBooking.seatNo")
    @Mapping(target = "vagonIndex", source = "seatBooking.vagon_id")
    BaseTicket toTicket(TicketRequest request);
}
