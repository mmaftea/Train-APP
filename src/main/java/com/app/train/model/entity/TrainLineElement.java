package com.app.train.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "trainline_element")
public class TrainLineElement {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trainLine", nullable = false)
    private TrainLine trainLine;

    @ManyToOne
    @JoinColumn(name = "station", nullable = false)
    private Station station;

    private int km;
}