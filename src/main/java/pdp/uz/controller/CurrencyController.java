package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.CurrencyAddDto;
import pdp.uz.model.CurrencyDto;
import pdp.uz.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<CurrencyDto>>> get(){
        return currencyService.get();
    }

    @GetMapping("/get/active")
    public ResponseEntity<ApiResponse<List<CurrencyDto>>> getActive(){
        return currencyService.getActive();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CurrencyDto>> add(@RequestBody CurrencyAddDto dto){
        return currencyService.add(dto);
    }

}
