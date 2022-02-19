package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.model.*;
import pdp.uz.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/api/output/product")
public class OutputProductController {
    private final OutputProductService outputProductService;

    @Autowired
    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<OutputProductDto>> add(@RequestBody OutputProductAddDto dto){
        return outputProductService.add(dto);
    }

    @PostMapping("/add/all")
    public ResponseEntity<ApiResponse<List<OutputProductDto>>> addAll(@RequestBody List<OutputProductAddDto> dto){
        return outputProductService.addAll(dto);
    }
}
