package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.dto.Currencies;
import com.kulcorp.CRUD.dto.ExchangeRate;
import com.kulcorp.CRUD.service.ExchangeRateServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cashMarket")
@AllArgsConstructor
public class MarketController {

    ExchangeRateServices services;

    @PostMapping(value = "/getCourse")
    public List<ExchangeRate> getCourse(@ModelAttribute("currencies") Currencies currencies){
        return services.getCourse(currencies.getCurrencies());
    }
}
