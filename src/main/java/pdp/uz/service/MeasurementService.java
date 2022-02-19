package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Measurement;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.MeasurementAddDto;
import pdp.uz.model.MeasurementDto;

import java.util.List;

public interface MeasurementService {

    ResponseEntity<ApiResponse<List<MeasurementDto>>> getAll();

    ResponseEntity<ApiResponse<MeasurementDto>> add(MeasurementAddDto dto);

    Measurement validate(Long id);
}
