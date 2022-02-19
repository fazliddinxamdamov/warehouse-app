package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Client;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.ClientAddDto;
import pdp.uz.model.ClientDto;

public interface ClientService {

    ResponseEntity<ApiResponse<ClientDto>> add(ClientAddDto dto);

    Client validate(Long id);
}
