package pdp.uz.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Supplier;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.SupplierAddDto;
import pdp.uz.model.SupplierDto;

import java.util.List;

public interface SupplierService {
    ResponseEntity<ApiResponse<List<SupplierDto>>> getAll(Pageable pageable);

    ResponseEntity<ApiResponse<SupplierDto>> get(Long id);

    ResponseEntity<ApiResponse<SupplierDto>> add(SupplierAddDto dto);

    ResponseEntity<ApiResponse<SupplierDto>> edit(Long id, String phoneNumber, String name);

    Supplier validate(Long id);
}
