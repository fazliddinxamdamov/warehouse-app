package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.OutputProductAddDto;
import pdp.uz.model.OutputProductDto;

import java.util.List;

public interface OutputProductService {
    ResponseEntity<ApiResponse<OutputProductDto>> add(OutputProductAddDto dto);

    ResponseEntity<ApiResponse<List<OutputProductDto>>> addAll(List<OutputProductAddDto> dto);
}
