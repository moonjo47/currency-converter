package com.smartsoft.converter.parser;

import com.smartsoft.converter.entities.Currency;

import java.util.List;

public interface Parser {

    List<Currency> parser(String xmlResult);
}
