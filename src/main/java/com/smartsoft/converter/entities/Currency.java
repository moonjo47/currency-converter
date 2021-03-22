package com.smartsoft.converter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "currencies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "currency_seq")
    private Long id;

    @Column(name = "numcode")
    private String numCode;

    @Column(name = "charcode")
    private String charCode;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;

    @Column(name = "nominal")
    private Long nominal;

    @Column(name = "date")
    private LocalDate date;

    public Currency(String numCode, String charCode, String name, Long nominal, Double value, LocalDate date) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
        this.nominal = nominal;
        this.value = value;
        this.date = date;
    }
}
