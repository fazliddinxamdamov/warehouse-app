package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Input;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.InputAddDto;
import pdp.uz.model.InputDto;

public interface InputService {
    ResponseEntity<ApiResponse<InputDto>> add(InputAddDto dto);
}
