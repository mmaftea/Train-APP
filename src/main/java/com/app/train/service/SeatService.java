package com.app.train.service;

import com.app.train.model.entity.Travel;

public interface SeatService {
    boolean isThereEnoughRoom(Travel travel);
    boolean isSeatAvailableOnTravel(Travel travel,int seatNumber);
}
