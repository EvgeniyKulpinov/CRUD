package com.kulcorp.CRUD.service.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulcorp.CRUD.dto.api.GeneralResponseDTO;
import com.kulcorp.CRUD.dto.api.ResponseExchangerateDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangerateApiServices implements CurrencyClientService {

    @Value("${keys.keyExchangerate}")
    private String key;

    @Value("${urls.urlExchangerate}")
    private String urlExchangerate;

    public ResponseExchangerateDTO getCourseFromExchangerate() {
        String url = urlExchangerate + key + "/latest/USD";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(
                    restTemplate.getForEntity(url, String.class).getBody(), ResponseExchangerateDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GeneralResponseDTO getData() {
        GeneralResponseDTO responseDTO  = new GeneralResponseDTO();
        ResponseExchangerateDTO responseExchangerateDTO = getCourseFromExchangerate();
        responseDTO.setBase(responseExchangerateDTO.getBase_code());
        responseDTO.setExchangeRates(responseExchangerateDTO.getConversion_rates());
        responseDTO.setSiteName("Exchangerate");
        return responseDTO;
    }
}
