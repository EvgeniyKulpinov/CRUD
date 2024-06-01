package com.kulcorp.CRUD.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulcorp.CRUD.dto.ExchangeRates;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRatesServices {

    public List<ExchangeRates> parser(List<String> list) {

        ObjectMapper mapper = new ObjectMapper();

        List<ExchangeRates> exchangeRatesList = new ArrayList<>();

        List<String> currencies = new ArrayList<>();
        currencies.add("RUB");
        currencies.add("EUR");

        try {
            JsonNode nodeFromAbstractapi = mapper.readTree(list.get(0));
            JsonNode nodeFromExchangerate = mapper.readTree(list.get(1));

            for (String currency : currencies) {
                ExchangeRates exchangeRates1 = new ExchangeRates();
                exchangeRates1.setCurrencyPair1(nodeFromAbstractapi.get("base").asText() + currency);
                double course1 = nodeFromAbstractapi.get("exchange_rates").get(currency).asDouble();
                exchangeRates1.setCourse1(course1);
                exchangeRates1.setCurrencyPair2(currency + nodeFromAbstractapi.get("base").asText());
                double reverseCourse1 = 1 / nodeFromExchangerate.get("conversion_rates").get(currency).asDouble();
                exchangeRates1.setCourse2(reverseCourse1);
                double benefit1 = (course1 * reverseCourse1 - 1) * 100;
                exchangeRates1.setBenefit((int) benefit1 + " %");
                exchangeRatesList.add(exchangeRates1);

                ExchangeRates exchangeRates2 = new ExchangeRates();
                exchangeRates2.setCurrencyPair1(currency + nodeFromAbstractapi.get("base").asText());
                double course2 = 1 / nodeFromAbstractapi.get("exchange_rates").get(currency).asDouble();
                exchangeRates2.setCourse1(course2);
                exchangeRates2.setCurrencyPair2(nodeFromAbstractapi.get("base").asText() + currency);
                double reverseCourse2 = nodeFromExchangerate.get("conversion_rates").get(currency).asDouble();
                exchangeRates2.setCourse2(reverseCourse2);
                double benefit2 = (course2 * reverseCourse2 - 1) * 100;
                exchangeRates2.setBenefit((int) benefit2 + " %");
                exchangeRatesList.add(exchangeRates2);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return exchangeRatesList;
    }
}
