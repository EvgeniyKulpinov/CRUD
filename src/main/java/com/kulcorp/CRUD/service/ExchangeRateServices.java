package com.kulcorp.CRUD.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulcorp.CRUD.dto.ExchangeRate;
import com.kulcorp.CRUD.dto.Response;
import com.kulcorp.CRUD.dto.ResponseAbstractapi;
import com.kulcorp.CRUD.dto.ResponseExchangerate;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeRateServices {

    private final Environment env;

    public List<ExchangeRate> getCourse(List<String> currencies) {

        List<ExchangeRate> exchangeRatesList = new ArrayList<>();
        Response response1 = getCourseFromAbstractapi();
        Response response2 = getCourseFromExchangerate();

        for (String currency : currencies) {

            double course1 = response1.getExchangeRates().get(currency);
            double reverseCourse1 = 1 / response2.getExchangeRates().get(currency);
            double benefit1 = (course1 * reverseCourse1 - 1) * 100;

            ExchangeRate rates1 = new ExchangeRate();

            rates1.setCurrencyPair1(response1.getBase() + currency);
            rates1.setCourse1(course1);
            rates1.setCurrencyPair2(currency + response1.getBase());
            rates1.setCourse2(reverseCourse1);
            rates1.setBenefit((int) benefit1 + " %");

            exchangeRatesList.add(rates1);

            ExchangeRate rates2 = new ExchangeRate();

            double course2 = 1 / response1.getExchangeRates().get(currency);
            double reverseCourse2 = response2.getExchangeRates().get(currency);
            double benefit2 = (course2 * reverseCourse2 - 1) * 100;

            rates2.setCurrencyPair1(currency + response2.getBase());
            rates2.setCourse1(course2);
            rates2.setCurrencyPair2(response2.getBase() + currency);
            rates2.setCourse2(reverseCourse2);
            rates2.setBenefit((int) benefit2 + " %");

            exchangeRatesList.add(rates2);
        }
        return exchangeRatesList;
    }

    public Response getCourseFromAbstractapi() {
        String key = env.getProperty("keyAbstractapi");
        String url = "https://exchange-rates.abstractapi.com/v1/live/";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", key)
                .queryParam("base", "USD")
                .queryParam("target", "EUR,RUB,CNY")
                .encode()
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return new Response(mapper.readValue(
                    restTemplate.getForEntity(urlTemplate, String.class).getBody(), ResponseAbstractapi.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getCourseFromExchangerate() {
        String key = env.getProperty("keyExchangerate");
        String url = "https://v6.exchangerate-api.com/v6/" + key + "/latest/USD";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return new Response(mapper.readValue(
                    restTemplate.getForEntity(url, String.class).getBody(), ResponseExchangerate.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
