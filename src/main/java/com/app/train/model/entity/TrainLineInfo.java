package com.app.train.model.entity;

import lombok.Data;

@Data
public class TrainLineInfo {
    private TrainLine trainLine;
    private int elementCount;

    public TrainLineInfo(TrainLine trainLine, long elementCount) {
        this.trainLine = trainLine;
        this.elementCount = (int) elementCount;
    }


    public TrainLineInfo() {
    }
}
