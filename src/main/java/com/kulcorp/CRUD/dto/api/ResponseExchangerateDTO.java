package com.kulcorp.CRUD.dto.api;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseExchangerateDTO {

    private String base_code;

    private Map<String, Double> conversion_rates;
}