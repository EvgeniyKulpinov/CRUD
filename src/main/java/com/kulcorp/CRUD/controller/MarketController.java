package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.dto.CurrenciesDTO;
import com.kulcorp.CRUD.dto.GeneralRateDTO;
import com.kulcorp.CRUD.service.api.GeneralRateServices;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cashMarket")
@AllArgsConstructor
public class MarketController {

    GeneralRateServices services;

    private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

    @PostMapping(value = "/getCourse")
    public List<GeneralRateDTO> getCourse(@ModelAttribute("currencies") CurrenciesDTO currencies){

        if (currencies.getCurrencies() == null){
            logger.info("The user did not select a currency");
            return null;
        }
        return services.getCourse(currencies.getCurrencies());
    }
}
