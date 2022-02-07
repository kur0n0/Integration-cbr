package ru.example.numbers.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.numbers.model.CurrencyDto;
import ru.example.numbers.service.CurrencyService;

@RestController
@RequestMapping(value = "api/rate")
public class MainController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping(value = "/currency")
    public CurrencyDto parseCurrency(@RequestParam String isoCode) throws JSONException {
        return currencyService.getCurrency(isoCode);
    }
}
