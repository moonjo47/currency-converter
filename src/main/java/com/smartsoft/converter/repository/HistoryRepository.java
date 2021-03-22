package com.smartsoft.converter.repository;

import com.smartsoft.converter.entities.Currency;
import com.smartsoft.converter.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByCurrencyFromAndCurrencyToAndDate(Currency currencyFrom, Currency currencyTo, LocalDate date);
}
