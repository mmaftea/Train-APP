package com.app.train.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trainvagon")
public class TrainVagon implements HasId<Integer> {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_loaded_vagons_id")
    private TrainLoadedVagon trainLoadedVagons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vagon_id")
    private Vagon vagon;

    @Column(name = "vagon_index")
    private Integer vagonIndex;

}