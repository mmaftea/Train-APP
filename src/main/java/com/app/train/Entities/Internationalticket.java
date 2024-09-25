package com.app.train.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "internationalticket")
public class Internationalticket {
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