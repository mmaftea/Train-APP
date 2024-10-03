package com.app.train.model.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class Person {
    private String firstName;
    private String lastName;
    @Nullable
    private int seatNumber;
}
