package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Supplier;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.SupplierAddDto;
import pdp.uz.model.SupplierDto;
import pdp.uz.repository.SupplierRepo;
import pdp.uz.service.SupplierService;

import java.util.List;
import java.util.Optional;

import static pdp.uz.model.ApiResponse.response;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepo supplierRepo;
    private final MapstructMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepo supplierRepo, MapstructMapper mapper) {
        this.supplierRepo = supplierRepo;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<SupplierDto>>> getAll(Pageable pageable) {
        List<Supplier> suppliers = supplierRepo.findAll(pageable).getContent();
        List<SupplierDto> resultList = mapper.toSupplierDto(suppliers);
        Long totalCount = supplierRepo.count();
        return response(resultList, totalCount);
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> get(Long id) {
        Supplier supplier = validate(id);
        return response(mapper.toSupplierDto(supplier));
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> add(SupplierAddDto dto) {
        String phoneNumber = dto.getPhoneNumber();
        if (Utils.isEmpty(phoneNumber)) {
            return response("Phone number should not be null", HttpStatus.BAD_REQUEST);
        }
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            return response("Name should not be null", HttpStatus.BAD_REQUEST);
        }
        Optional<Supplier> optionalSupplier = supplierRepo.findByPhoneNumber(phoneNumber);
        if (optionalSupplier.isPresent()) {
            return response("Supplier with this phone number has already existed", HttpStatus.FORBIDDEN);
        }
        Supplier supplier = mapper.toSupplier(dto);
        Supplier savedSupplier = supplierRepo.save(supplier);
        return response(mapper.toSupplierDto(savedSupplier), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> edit(Long id, String phoneNumber, String name) {
        if (Utils.isEmpty(phoneNumber)) {
            return response("Phone number should not be null", HttpStatus.BAD_REQUEST);
        }
        if (Utils.isEmpty(name)) {
            return response("Name should not be null", HttpStatus.BAD_REQUEST);
        }

        Supplier supplier = validate(id);

        if (supplierRepo.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            return response("Supplier with this phone number has already existed", HttpStatus.FORBIDDEN);
        }

        supplier.setPhoneNumber(phoneNumber);
        supplier.setName(name);
        Supplier savedSupplier = supplierRepo.save(supplier);
        return response(mapper.toSupplierDto(savedSupplier), HttpStatus.ACCEPTED);
    }

    public Supplier validate(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (!optionalSupplier.isPresent()) {
            throw new RuntimeException("Supplier id = " + id + ", not found!");
        }
        Supplier supplier = optionalSupplier.get();
        if (!supplier.getActive()) {
            throw new RuntimeException("Supplier id = " + id + ", is inactive!");
        }
        return supplier;
    }
}
