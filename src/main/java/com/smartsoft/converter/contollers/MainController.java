package com.smartsoft.converter.contollers;

import com.smartsoft.converter.dto.HistoryDTO;
import com.smartsoft.converter.entities.History;
import com.smartsoft.converter.services.CurrencyService;
import com.smartsoft.converter.services.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    private CurrencyService currencyService;
    private HistoryService historyService;

    public MainController(CurrencyService currencyService, HistoryService historyService) {
        this.currencyService = currencyService;
        this.historyService = historyService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Конвертер");
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("histories", historyService.findAll());
        model.addAttribute("history", new History());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/calculate")
    @ResponseBody
    public History calc(@RequestBody HistoryDTO historyDto) {

        History history = new History();
        history.setCurrencyFrom(currencyService.findById(historyDto.getCurrencyFrom()));
        history.setCurrencyTo(currencyService.findById(historyDto.getCurrencyTo()));
        history.setOriginalAmount(historyDto.getOriginalAmount());

        System.out.println("-----------------------------------------");
        System.out.println(history.getOriginalAmount());
        System.out.println(history.getCurrencyFrom().getName());
        System.out.println(history.getCurrencyTo().getName());

        // посчитать
        double receivedAmount = currencyService.calculate(history);
        System.out.println(receivedAmount);
        history.setReceivedAmount(receivedAmount);

        historyService.save(history);

        return history;
    }

    @PostMapping("/search")
    @ResponseBody
    public List<History> search(@RequestBody HistoryDTO historyDto) {

        List<History> histories = historyService.searchHistories(
                currencyService.findById(historyDto.getCurrencyFrom()),
                currencyService.findById(historyDto.getCurrencyTo()),
                historyDto.getDate());

        for (History tempHistory : histories) {
            System.out.println(tempHistory);
        }

        return histories;
    }

    @GetMapping("/reload")
    @ResponseBody
    public List<History> reloadDataHistory() {
        List<History> histories = historyService.findAll();

        return histories;
    }
}
