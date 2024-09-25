package com.app.train.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trainvagon")
public class Trainvagon {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_loaded_vagons_id")
    private Trainloadedvagon trainLoadedVagons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vagon_id")
    private Vagon vagon;

    @Column(name = "vagon_index")
    private Integer vagonIndex;

}