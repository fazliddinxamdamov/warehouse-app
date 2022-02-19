package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.InputProductAddDto;
import pdp.uz.model.InputProductDto;
import pdp.uz.model.resp.ProductReport;

import java.time.LocalDate;
import java.util.List;

public interface InputProductService {

    ResponseEntity<ApiResponse<InputProductDto>> add(InputProductAddDto dto);

    ResponseEntity<ApiResponse<List<InputProductDto>>> addAll(List<InputProductAddDto> dto);

    ResponseEntity<ApiResponse<List<ProductReport>>> get();

    ResponseEntity<ApiResponse<List<ProductReport>>> get(String date);
}
