package com.app.train.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UtilityResult {
    private double km;
    private long minutes;
}
