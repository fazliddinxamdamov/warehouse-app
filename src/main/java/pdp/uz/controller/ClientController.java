package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.ClientAddDto;
import pdp.uz.model.ClientDto;
import pdp.uz.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ClientDto>> add(@Valid @RequestBody ClientAddDto dto) {
        return clientService.add(dto);
    }
}
