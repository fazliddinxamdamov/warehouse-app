package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Warehouse;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.WarehouseAddDto;
import pdp.uz.model.WarehouseDto;
import pdp.uz.repository.WarehouseRepo;
import pdp.uz.service.WarehouseService;
import static pdp.uz.model.ApiResponse.response;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepo warehouseRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepo warehouseRepo, MapstructMapper mapstructMapper) {
        this.warehouseRepo = warehouseRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<WarehouseDto>>> getList() {
        List<Warehouse> list = warehouseRepo.findAll();
        return response(mapstructMapper.toWarehouseDto(list));
    }

    @Override
    public ResponseEntity<ApiResponse<WarehouseDto>> add(WarehouseAddDto dto) {
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            return response("Warehouse name should not be null!", HttpStatus.BAD_REQUEST);
        } else {
            Optional<Warehouse> warehouseOpt = warehouseRepo.findByName(name);
            if (warehouseOpt.isPresent()) {
                return response("This name is already exist!", HttpStatus.FORBIDDEN);
            }
        }
        Warehouse warehouse = mapstructMapper.toWarehouse(dto);
        Warehouse savedWarehouse = warehouseRepo.save(warehouse);
        return response(mapstructMapper.toWarehouseDto(savedWarehouse), HttpStatus.CREATED);
    }

    @Override
    public boolean active(Warehouse warehouse) {
        if (Utils.isEmpty(warehouse)) {
            return false;
        }
        return warehouse.getActive();
    }

    public Warehouse validate(Long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (!optionalWarehouse.isPresent()) {
            throw new RuntimeException("Warehouse id = " + id + ", not found!");
        }
        Warehouse warehouse = optionalWarehouse.get();
        if (!warehouse.getActive()) {
            throw new RuntimeException("Category id = " + id + ", is inactive!");
        }
        return warehouse;
    }
}
