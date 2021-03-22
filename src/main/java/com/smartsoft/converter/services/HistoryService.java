package com.smartsoft.converter.services;

import com.smartsoft.converter.entities.Currency;
import com.smartsoft.converter.entities.History;
import com.smartsoft.converter.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    public List<History> histories;

    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.histories = new ArrayList<>();
        this.historyRepository = historyRepository;
    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public void save(History history) {
        historyRepository.save(history);
    }

    public List<History> searchHistories(Currency currencyFrom, Currency currencyTo, LocalDate date) {
        return historyRepository.findByCurrencyFromAndCurrencyToAndDate(currencyFrom,currencyTo, date);
    }
}
