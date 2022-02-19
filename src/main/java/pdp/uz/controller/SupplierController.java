package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.SupplierAddDto;
import pdp.uz.model.SupplierDto;
import pdp.uz.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<SupplierDto>>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return supplierService.getAll(pageable);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<SupplierDto>> get(@PathVariable(value = "id") Long id) {
        return supplierService.get(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SupplierDto>> add(@RequestBody SupplierAddDto dto) {
        return supplierService.add(dto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<SupplierDto>> edit(@PathVariable(value = "id") Long id,
                                                         @RequestParam String phoneNumber,
                                                         @RequestParam String name) {
        return supplierService.edit(id, phoneNumber, name);
    }
}
