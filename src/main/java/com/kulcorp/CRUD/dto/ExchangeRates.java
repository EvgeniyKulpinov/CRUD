package com.kulcorp.CRUD.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExchangeRates {

    private String currencyPair1;

    private double course1;

    private String currencyPair2;

    private double course2;

    private String benefit;
}