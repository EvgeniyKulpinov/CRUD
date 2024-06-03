package com.kulcorp.CRUD.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Response {

        private String base;

        private Map<String, Double> exchangeRates;

    public Response(ResponseAbstractapi abstractapi) {
        this.exchangeRates = abstractapi.getExchange_rates();
        this.base = abstractapi.getBase();
    }

    public Response(ResponseExchangerate exchangerate) {
        this.exchangeRates = exchangerate.getConversion_rates();
        this.base = exchangerate.getBase_code();
    }
}
