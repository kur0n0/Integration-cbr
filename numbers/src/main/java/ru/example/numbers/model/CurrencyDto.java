package ru.example.numbers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDto {

    @JsonProperty(value = "Date")
    private String date;
    private Valute valute;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Valute getValute() {
        return valute;
    }

    public void setValute(Valute valute) {
        this.valute = valute;
    }
}
