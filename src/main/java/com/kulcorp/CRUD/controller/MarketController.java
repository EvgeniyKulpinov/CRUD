package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.dto.CurrenciesDTO;
import com.kulcorp.CRUD.dto.GeneralRateDTO;
import com.kulcorp.CRUD.service.api.GeneralRateServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cashMarket")
@AllArgsConstructor
public class MarketController {

    GeneralRateServices services;

    @PostMapping(value = "/getCourse")
    public List<GeneralRateDTO> getCourse(@ModelAttribute("currencies") CurrenciesDTO currencies){
        return services.getCourse(currencies.getCurrencies());
    }
}
