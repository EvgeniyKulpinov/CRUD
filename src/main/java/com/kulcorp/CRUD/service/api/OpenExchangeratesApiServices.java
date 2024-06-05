package com.kulcorp.CRUD.service.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulcorp.CRUD.dto.api.ResponseOpenexchangeratesDTO;
import com.kulcorp.CRUD.dto.api.GeneralResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenExchangeratesApiServices implements CurrencyClientService{

    @Value("${keys.keyOpenexchangerates}")
    private String key;

    @Value("${urls.urlOpenexchangerates}")
    private String url;

    public ResponseOpenexchangeratesDTO getCourseFromOpenexchangerates() {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("app_id", key)
                .queryParam("base", "USD")
                .encode()
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(restTemplate.getForEntity(urlTemplate, String.class)
                    .getBody(), ResponseOpenexchangeratesDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GeneralResponseDTO getData() {
        GeneralResponseDTO responseDTO  = new GeneralResponseDTO();
        ResponseOpenexchangeratesDTO openexchangeratesDTO = getCourseFromOpenexchangerates();
        responseDTO.setBase(openexchangeratesDTO.getBase());
        responseDTO.setExchangeRates(openexchangeratesDTO.getRates());
        responseDTO.setSiteName("Openexchangerates");
        return responseDTO;
    }
}
