package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.InputProductAddDto;
import pdp.uz.model.InputProductDto;
import pdp.uz.model.resp.ProductReport;
import pdp.uz.service.InputProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/input/product")
public class InputProductController {
    private final InputProductService inputProductService;

    @Autowired
    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<InputProductDto>> add(@Valid @RequestBody InputProductAddDto dto) {
        return inputProductService.add(dto);
    }

    @PostMapping("/add/all")
    public ResponseEntity<ApiResponse<List<InputProductDto>>> addAll(@RequestBody List<InputProductAddDto> dto) {
        return inputProductService.addAll(dto);
    }

    // Dashboarc
    @GetMapping("/get/today")
    public ResponseEntity<ApiResponse<List<ProductReport>>> get() {
        return inputProductService.get();
    }

    @GetMapping("/get/date")
    public ResponseEntity<ApiResponse<List<ProductReport>>> get(@RequestParam String date) {
        return inputProductService.get(date);
    }
}
