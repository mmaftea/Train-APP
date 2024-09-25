package com.app.train.model.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "passdocumentdata")
public class Passdocumentdatum implements HasId<String>{
    @Id
    @Size(max = 16)
    @Column(name = "idnp", nullable = false, length = 16)
    private String idnp;

    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Name must start with an uppercase letter and contain only letters.")
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Surname must start with an uppercase letter and contain only letters.")
    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "date_of_expiry")
    private LocalDate dateOfExpiry;

    @Override
    public String getId() {
        return idnp;
    }
}