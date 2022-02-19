package pdp.uz.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Currency;
import pdp.uz.entity.Input;
import pdp.uz.entity.Supplier;
import pdp.uz.entity.Warehouse;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.InputAddDto;
import pdp.uz.model.InputDto;
import pdp.uz.repository.InputRepo;
import pdp.uz.service.CurrencyService;
import pdp.uz.service.InputService;
import pdp.uz.service.SupplierService;
import pdp.uz.service.WarehouseService;
import static pdp.uz.model.ApiResponse.response;

import java.time.LocalDateTime;

@Service
public class InputServiceImpl implements InputService {
    private final InputRepo inputRepo;
    private final MapstructMapper mapper;
    private final WarehouseService warehouseService;
    private final SupplierService supplierService;
    private final CurrencyService currencyService;

    public InputServiceImpl(InputRepo inputRepo, MapstructMapper mapper, WarehouseService warehouseService, SupplierService supplierService, CurrencyService currencyService) {
        this.inputRepo = inputRepo;
        this.mapper = mapper;
        this.warehouseService = warehouseService;
        this.supplierService = supplierService;
        this.currencyService = currencyService;
    }

    @Override
    public ResponseEntity<ApiResponse<InputDto>> add(InputAddDto dto) {
        Long currencyId = dto.getCurrencyId();
        if (Utils.isEmpty(currencyId)){
            return response("Currency id should not be null!", HttpStatus.BAD_REQUEST);
        }
        String featureNumber = dto.getFeatureNumber();
        if (Utils.isEmpty(featureNumber)){
            return response("Feature number should not be null!", HttpStatus.BAD_REQUEST);
        }
        Long supplierId = dto.getSupplierId();
        if (Utils.isEmpty(supplierId)){
             return response("Supplier id should not be null!", HttpStatus.BAD_REQUEST);
        }
        Long warehouseId = dto.getWarehouseId();
        if (Utils.isEmpty(warehouseId)){
            return response("Warehouse id should not be null!", HttpStatus.BAD_REQUEST);
        }
        Supplier supplier = supplierService.validate(supplierId);
        Currency currency = currencyService.validate(currencyId);
        Warehouse warehouse = warehouseService.validate(warehouseId);

        Input input = new Input();
        input.setCode(Utils.generateCode());
        input.setCurrency(currency);
        input.setDate(LocalDateTime.now());
        input.setFeatureNumber(featureNumber);
        input.setSupplier(supplier);
        input.setWarehouse(warehouse);
        Input savedInput = inputRepo.save(input);

        return response(mapper.toInputDto(savedInput), HttpStatus.CREATED);
    }


}
