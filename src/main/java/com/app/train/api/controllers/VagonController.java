package com.app.train.api.controllers;

import com.app.train.dao.interfaces.TrainVagonRepository;
import com.app.train.dao.interfaces.VagonRepository;
import com.app.train.model.entity.Vagon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/wagons")
public class VagonController {

    private final TrainVagonRepository trainVagonRepository;
    private final VagonRepository vagonRepository;

    @GetMapping("/{id}")
    public Vagon getById(@RequestParam Integer id) {
        return vagonRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Vagon> getByTravel(@RequestParam Integer id) {
        return trainVagonRepository.getVagonsByTravel(id);
    }
}
