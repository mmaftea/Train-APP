package com.app.train.model.dto;

import com.app.train.model.entity.Station;
import com.app.train.model.entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TicketMetaData {
    private Station startStation;
    private LocalDateTime departureTime;
    private Station stopStation;
    private LocalDateTime arrivalTime;
    private Travel travel;
}
