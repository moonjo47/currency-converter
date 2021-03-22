package com.smartsoft.converter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "history_seq")
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "currency_from")
    private Currency currencyFrom;

    @ManyToOne()
    @JoinColumn(name = "currency_to")
    private Currency currencyTo;

    @Column(name = "original_amount")
    private Double originalAmount;

    @Column(name = "received_amount")
    private Double receivedAmount;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private LocalDate date;


    public History(Currency currencyFrom, Currency currencyTo, Double originalAmount,
                   Double receivedAmount) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.originalAmount = originalAmount;
        this.receivedAmount = receivedAmount;
    }

    public History(Currency currencyFrom, Currency currencyTo, Double originalAmount) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.originalAmount = originalAmount;
    }
}
