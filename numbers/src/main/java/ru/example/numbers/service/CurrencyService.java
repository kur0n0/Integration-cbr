package ru.example.numbers.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.example.numbers.model.CurrencyDto;
import ru.example.numbers.model.Valute;

@Service
public class CurrencyService {

    public CurrencyDto getCurrency(String isoCode) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, "application/json");
        HttpEntity requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("https://www.cbr-xml-daily.ru/daily_json.js", HttpMethod.GET, requestEntity, String.class);

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());

        JSONObject valuteObject = jsonObject.getJSONObject("Valute").getJSONObject(isoCode);
        Valute valute = new Valute();
        valute.setCharCode(valuteObject.getString("CharCode"));
        valute.setId(valuteObject.getString("ID"));
        valute.setValue(valuteObject.getBigDecimal("Value"));
        valute.setNominal(valuteObject.getBigDecimal("Nominal").toString());
        valute.setName(valuteObject.getString("Name"));
        valute.setNumCode(valuteObject.getString("NumCode"));

        CurrencyDto currencyDto = new CurrencyDto();
        valute.setPrevious(valuteObject.getBigDecimal("Previous"));
        currencyDto.setDate(jsonObject.getString("Date"));
        currencyDto.setValute(valute);
        return currencyDto;
    }
}
