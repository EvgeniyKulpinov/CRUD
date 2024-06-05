package com.kulcorp.CRUD.dto.api;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAbstractapiDTO {

    private String base;

    private Map<String, Double> exchange_rates;
}