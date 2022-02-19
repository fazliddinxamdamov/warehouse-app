package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.MeasurementAddDto;
import pdp.uz.model.MeasurementDto;
import pdp.uz.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<MeasurementDto>>> getAll() {
        return measurementService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<MeasurementDto>> add(@RequestBody MeasurementAddDto dto) {
        return measurementService.add(dto);
    }
}
