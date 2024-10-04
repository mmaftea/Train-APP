package com.app.train.service;

import com.app.train.model.dto.TravelResult;
import com.app.train.model.dto.UtilityResult;

public interface UtilityService {
    UtilityResult calculateDistanceAndDuration(TravelResult travelResult);

    long calculateDuration(double km);
}
