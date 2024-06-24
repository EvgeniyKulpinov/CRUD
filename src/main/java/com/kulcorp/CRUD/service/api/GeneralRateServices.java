package com.kulcorp.CRUD.service.api;

import com.kulcorp.CRUD.dto.GeneralRateDTO;
import com.kulcorp.CRUD.dto.api.GeneralResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GeneralRateServices {

    private List<CurrencyClientService> currencyClientServices;

    public List<GeneralRateDTO> getCourse(List<String> currencies) {

        List<GeneralRateDTO> exchangeRatesList = new ArrayList<>();

        List<GeneralResponseDTO> responseDTOS = new ArrayList<>();

        for (CurrencyClientService currencyClientService : currencyClientServices) {
            responseDTOS.add(currencyClientService.getData());
        }

        for (int i = 0; i < responseDTOS.size(); i++) {
            for (int j = 0; j < responseDTOS.size(); j++) {
                if (i != j) {
                    GeneralResponseDTO response1 = responseDTOS.get(i);
                    GeneralResponseDTO response2 = responseDTOS.get(j);
                    for (String currency : currencies) {
                        GeneralRateDTO rates = new GeneralRateDTO();

                        double course = response1.getExchangeRates().get(currency);
                        String formattedCourse = String.format("%.5f", course);
                        double reverseCourse = 1 / response2.getExchangeRates().get(currency);
                        String formattedReverseCourse = String.format("%.5f", reverseCourse);
                        double benefit = (course * reverseCourse - 1) * 100;
                        String formattedBenefit = String.format("%.2f", benefit);

                        rates.setCurrencyPair1(response1.getBase() + currency);
                        rates.setCourse1(Double.parseDouble(formattedCourse));
                        rates.setSiteName1(response1.getSiteName());
                        rates.setCurrencyPair2(currency + response1.getBase());
                        rates.setCourse2(Double.parseDouble(formattedReverseCourse));
                        rates.setSiteName2(response2.getSiteName());
                        rates.setBenefit(Double.parseDouble(formattedBenefit) + " %");
                        if (benefit > 0) {
                            exchangeRatesList.add(rates);
                        }
                    }
                }
            }
        }
        exchangeRatesList.sort((o1, o2) -> o2.getBenefit().compareTo(o1.getBenefit()));
        return exchangeRatesList;
    }
}
