package com.kulcorp.CRUD.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseExchangerate {

    private String base_code;

    private Map<String, Double> conversion_rates;
}