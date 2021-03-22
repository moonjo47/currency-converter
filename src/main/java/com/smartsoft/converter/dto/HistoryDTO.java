package com.smartsoft.converter.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class HistoryDTO {
    private long currencyFrom;
    private long currencyTo;
    private double originalAmount;
    private double receivedAmount;
    private LocalDate date;

    public HistoryDTO(long currencyFrom, long currencyTo, LocalDate date) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.date = date;
    }

    public HistoryDTO(long currencyFrom, long currencyTo, double originalAmount) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.originalAmount = originalAmount;
    }

}
