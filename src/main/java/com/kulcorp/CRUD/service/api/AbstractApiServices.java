package com.kulcorp.CRUD.service.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulcorp.CRUD.dto.api.GeneralResponseDTO;
import com.kulcorp.CRUD.dto.api.ResponseAbstractapiDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AbstractApiServices implements CurrencyClientService {

    @Value("${keys.keyAbstractapi}")
    private String key;

    @Value("${urls.urlAbstractapi}")
    private String url;

    public ResponseAbstractapiDTO getCourseFromAbstractapi() {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", key)
                .queryParam("base", "USD")
                .queryParam("target", "EUR,RUB,CNY")
                .encode()
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(restTemplate.getForEntity(urlTemplate, String.class)
                    .getBody(), ResponseAbstractapiDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GeneralResponseDTO getData() {
        GeneralResponseDTO responseDTO  = new GeneralResponseDTO();
        ResponseAbstractapiDTO responseAbstractapiDTO = getCourseFromAbstractapi();
        responseDTO.setBase(responseAbstractapiDTO.getBase());
        responseDTO.setExchangeRates(responseAbstractapiDTO.getExchange_rates());
        responseDTO.setSiteName("Abstractapi");
        return responseDTO;
    }
}
