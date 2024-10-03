package com.app.train.model.dto;

import com.app.train.model.entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelResult {
    private Travel travel;
    private int startId;
    private int endId;
}
