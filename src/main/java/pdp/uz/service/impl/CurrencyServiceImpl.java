package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Currency;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.CurrencyAddDto;
import pdp.uz.model.CurrencyDto;
import pdp.uz.repository.CurrencyRepo;
import pdp.uz.service.CurrencyService;

import java.util.List;
import java.util.Optional;

import static pdp.uz.model.ApiResponse.response;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepo currencyRepo;
    private final MapstructMapper mapper;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepo currencyRepo, MapstructMapper mapper) {
        this.currencyRepo = currencyRepo;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<CurrencyDto>>> get() {
        List<Currency> currencies = currencyRepo.findAll();
        return response (mapper.toCurrencyDto(currencies));
    }

    @Override
    public ResponseEntity<ApiResponse<List<CurrencyDto>>> getActive() {
        List<Currency> currencies = currencyRepo.findAllByActiveTrue();
        return response(mapper.toCurrencyDto(currencies));
    }

    @Override
    public ResponseEntity<ApiResponse<CurrencyDto>> add(CurrencyAddDto dto) {
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            return response("Currency name should not be null!", HttpStatus.BAD_REQUEST);
        }
        Optional<Currency> optionalCurrency = currencyRepo.findByNameAndActiveTrue(name);
        if (optionalCurrency.isPresent()){
            return response("This currency has already existed", HttpStatus.FORBIDDEN);
        }
        Currency currency = mapper.toCurrency(dto);
        Currency savedCurrency = currencyRepo.save(currency);
        return response(mapper.toCurrencyDto(savedCurrency), HttpStatus.CREATED);
    }

    public Currency validate(Long id) {
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (!optionalCurrency.isPresent()) {
            throw new RuntimeException("Currency id = " + id + ", not found!");
        }
        Currency currency = optionalCurrency.get();
        if (!currency.getActive()) {
            throw new RuntimeException("Currency id = " + id + ", is inactive!");
        }
        return currency;
    }
}
