package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.OutputAddDto;
import pdp.uz.model.OutputDto;

public interface OutputService {
    ResponseEntity<ApiResponse<OutputDto>> add(OutputAddDto dto);
}
