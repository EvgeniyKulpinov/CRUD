package com.kulcorp.CRUD.dto.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseOpenexchangeratesDTO {

    private String base;

    private Map<String, Double> rates;
}
