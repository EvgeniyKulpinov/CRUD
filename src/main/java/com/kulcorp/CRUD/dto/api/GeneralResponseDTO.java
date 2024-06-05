package com.kulcorp.CRUD.dto.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class GeneralResponseDTO {

        private String base;

        private Map<String, Double> exchangeRates;

        private String siteName;
}
