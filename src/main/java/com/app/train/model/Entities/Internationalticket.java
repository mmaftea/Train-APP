package com.app.train.model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "internationalticket")
public class Internationalticket implements HasId<Integer>{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inter_national_base_ticket_id")
    private Baseticket interNationalBaseTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inter_national_pass_document_data_id")
    private Passdocumentdatum interNationalPassDocumentData;

}