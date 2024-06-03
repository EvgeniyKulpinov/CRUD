package com.kulcorp.CRUD.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAbstractapi {

    private String base;

    private Map<String, Double> exchange_rates;
}