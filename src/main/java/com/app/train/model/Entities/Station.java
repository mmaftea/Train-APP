package com.app.train.model.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "station")
public class Station implements HasId<Integer>{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 2)
    @NotNull
    @Column(name = "country_code", nullable = false, length = 2)
    private String countryCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "station_name", nullable = false, length = 100)
    private String stationName;

    @Column(name = "station_lat", precision = 9, scale = 6)
    private BigDecimal stationLat;

    @Column(name = "station_long", precision = 9, scale = 6)
    private BigDecimal stationLong;

}