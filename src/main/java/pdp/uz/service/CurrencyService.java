package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Currency;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.CurrencyAddDto;
import pdp.uz.model.CurrencyDto;

import java.util.List;

public interface CurrencyService {

    ResponseEntity<ApiResponse<List<CurrencyDto>>> get();

    ResponseEntity<ApiResponse<List<CurrencyDto>>> getActive();

    ResponseEntity<ApiResponse<CurrencyDto>> add(CurrencyAddDto dto);

    Currency validate(Long id);
}
