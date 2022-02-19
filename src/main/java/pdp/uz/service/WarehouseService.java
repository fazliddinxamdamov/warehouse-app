package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Warehouse;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.WarehouseAddDto;
import pdp.uz.model.WarehouseDto;

import java.util.List;

public interface WarehouseService {

    ResponseEntity<ApiResponse<List<WarehouseDto>>> getList();

    ResponseEntity<ApiResponse<WarehouseDto>> add(WarehouseAddDto dto);

    boolean active(Warehouse warehouse);

    Warehouse validate(Long id);

}
