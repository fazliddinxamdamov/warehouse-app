package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.InputAddDto;
import pdp.uz.model.InputDto;
import pdp.uz.service.InputService;


@RestController
@RequestMapping("/api/input")
public class InputController {
    private final InputService inputService;

    @Autowired
    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<InputDto>> add(@RequestBody InputAddDto dto){
        return inputService.add(dto);
    }


}
