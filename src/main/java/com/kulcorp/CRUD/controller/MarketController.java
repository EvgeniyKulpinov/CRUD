package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.dto.ExchangeRates;
import com.kulcorp.CRUD.service.ExchangeRatesServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/cashMarket")
@AllArgsConstructor
public class MarketController {

    ExchangeRatesServices services;

    @GetMapping(value = "/getCourse")
    public List<ExchangeRates> getCourse(){
        List<String> list = new ArrayList<>();
        list.add(getCourseFromAbstractapi());
        list.add(getCourseFromExchangerate());
        return services.parser(list);
    }

    public String getCourseFromAbstractapi(){
        String url = "https://exchange-rates.abstractapi.com/v1/live/";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", "36bdc0604621481aabd457f97825d17e")
                .queryParam("base", "USD")
                .queryParam("target", "EUR,RUB")
                .encode()
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(urlTemplate, String.class);
        return response.getBody();
    }

    public String getCourseFromExchangerate(){
        String url = "https://v6.exchangerate-api.com/v6/c4add2d204650796af6b95ef/latest/USD";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
