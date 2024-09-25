package com.app.train.model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tax")
public class Tax implements HasId<Integer>{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_base_ticket_id")
    private Baseticket taxBaseTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_expense_id")
    private Expense taxExpense;

}