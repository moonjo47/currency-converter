package com.smartsoft.converter.services;

import com.smartsoft.converter.entities.Currency;
import com.smartsoft.converter.entities.History;
import com.smartsoft.converter.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public Currency findById(Long id) {
        return currencyRepository.findById(id).get();
    }

    public double calculate(History history) {
        double cross_currency = history.getCurrencyFrom().getValue() * history.getCurrencyFrom().getNominal() /
                history.getCurrencyTo().getValue() * history.getCurrencyTo().getNominal();
        double result = cross_currency * history.getOriginalAmount();
        NumberFormat formatter = new DecimalFormat("#0.00");
        return Double.parseDouble(formatter.format(result).replace(',', '.'));
    }
}
