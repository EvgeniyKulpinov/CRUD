package com.kulcorp.CRUD.service.api;

import com.kulcorp.CRUD.dto.api.GeneralResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyClientService {

    GeneralResponseDTO getData();
}
