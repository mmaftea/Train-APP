package com.app.train.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vagon_class")
public class VagonClass {
    @Id
    @Size(max = 25)
    @Column(name = "id", nullable = false, length = 25)
    private String id;

    @Column(name = "number_of_elements")
    private Integer numberOfElements;

    @Column(name = "stand_up_capacity")
    private Integer standUpCapacity;

    @Column(name = "class_price_index")
    private Float classPriceIndex;

}