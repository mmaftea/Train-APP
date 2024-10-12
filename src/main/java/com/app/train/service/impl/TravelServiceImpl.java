package com.app.train.service.impl;

import com.app.train.dao.interfaces.TravelRepository;
import com.app.train.model.entity.Travel;
import com.app.train.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    private final TravelRepository travelRepository;

    @Override
    public Travel findTravelById(int id) {
        return travelRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    @Override
    public Travel addNewApartment(Travel apartment) {
        return travelRepository.save(apartment);
    }

}
