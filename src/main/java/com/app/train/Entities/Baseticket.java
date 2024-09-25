package com.app.train.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "baseticket")
public class Baseticket {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @Column(name = "boarding_station_index")
    private Integer boardingStationIndex;

    @Column(name = "exit_station_index")
    private Integer exitStationIndex;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "vagon_index")
    private Integer vagonIndex;

    @Column(name = "price")
    private Float price;

}