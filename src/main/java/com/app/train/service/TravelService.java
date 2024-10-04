package com.app.train.service;

import com.app.train.model.entity.Travel;

import java.util.List;

public interface TravelService {
    Travel findTravelById(int id);

    List<Travel> getAllTravels();

    Travel addNewApartment(Travel apartment);
}
