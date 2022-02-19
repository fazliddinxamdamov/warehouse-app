package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Measurement;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.MeasurementAddDto;
import pdp.uz.model.MeasurementDto;
import pdp.uz.repository.MeasurementRepo;
import pdp.uz.service.MeasurementService;
import static pdp.uz.model.ApiResponse.response;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepo measurementRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepo measurementRepo, MapstructMapper mapstructMapper) {
        this.measurementRepo = measurementRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<MeasurementDto>>> getAll() {
        List<Measurement> list = measurementRepo.findAll();
        return response(mapstructMapper.toMeasurementDto(list));
    }

    @Override
    public ResponseEntity<ApiResponse<MeasurementDto>> add(MeasurementAddDto dto) {
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            return response("Measurement name should not be null!", HttpStatus.BAD_REQUEST);
        } else {
            Optional<Measurement> measurementOpt = measurementRepo.findByName(name);
            if (measurementOpt.isPresent()) {
                return response("This name is already exist!", HttpStatus.FORBIDDEN);
            }
        }
        Measurement measurement = mapstructMapper.toMeasurement(dto);
        Measurement savedMeasurement = measurementRepo.save(measurement);
        return response(mapstructMapper.toMeasurementDto(savedMeasurement), HttpStatus.CREATED);
    }

    @Override
    public Measurement validate(Long id) {
        Optional<Measurement> measurementOpt = measurementRepo.findById(id);
        if (!measurementOpt.isPresent()) {
            throw new RuntimeException("Measurement id = " + id + ", not found!");
        }
        Measurement measurement = measurementOpt.get();
        if (!measurement.getActive()) {
            throw new RuntimeException("Measurement id = " + id + ", is inactive!");
        }
        return measurement;
    }
}
