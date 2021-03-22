package com.smartsoft.converter.services;

import com.smartsoft.converter.entities.Currency;
import com.smartsoft.converter.entities.History;
import com.smartsoft.converter.http.HttpRequest;
import com.smartsoft.converter.parser.Parser;
import com.smartsoft.converter.repository.CurrencyRepository;
import com.smartsoft.converter.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class InitService {

    private CurrencyRepository currencyRepository;
    private HistoryRepository historyRepository;

    private List<Currency> currencies;
    private Parser parser;
    private HttpRequest request;

    public InitService(CurrencyRepository currencyRepository, Parser parser, HttpRequest request,
                       HistoryRepository historyRepository) {
        this.currencyRepository = currencyRepository;
        this.parser = parser;
        this.request = request;
        this.historyRepository = historyRepository;
    }

    public void init() {
        try {
            String xmlResult = request.request();
            currencies = parser.parser(xmlResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currencyRepository.saveAll(currencies);
        currencyRepository.save(new Currency("643", "RUB", "Российский рубль", 1L,
                Double.valueOf(1), LocalDate.now()));
//        loadFakeHistoriesData();
    }

    public void loadFakeHistoriesData() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        NumberFormat formatter = new DecimalFormat("#0.00");
        List<History> histories = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            histories.add(new History(
                    currencyRepository.getOne(threadLocalRandom.nextLong(32, 36)),
                    currencyRepository.getOne(threadLocalRandom.nextLong(32, 36)),
                    Double.valueOf(formatter.format(threadLocalRandom.nextDouble(1, 100)).
                            replace(',','.')),
                    Double.valueOf(formatter.format(threadLocalRandom.nextDouble(1, 100)).
                            replace(',','.'))));
//                    LocalDate.now()));
        }
        historyRepository.saveAll(histories);
    }
}
