package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Client;
import pdp.uz.entity.Supplier;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.ClientAddDto;
import pdp.uz.model.ClientDto;
import pdp.uz.repository.ClientRepo;
import pdp.uz.service.ClientService;

import java.util.Optional;

import static pdp.uz.model.ApiResponse.response;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;
    private final MapstructMapper mapper;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo, MapstructMapper mapper) {
        this.clientRepo = clientRepo;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ApiResponse<ClientDto>> add(ClientAddDto dto) {
        String phoneNumber = dto.getPhoneNumber();
        if (Utils.isEmpty(phoneNumber)) {
            return response("Phone number should not be null", HttpStatus.BAD_REQUEST);
        }
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            return response("Name should not be null", HttpStatus.BAD_REQUEST);
        }
        Optional<Client> optionalClient = clientRepo.findByPhoneNumber(phoneNumber);
        if (optionalClient.isPresent()) {
            return response("Client with this phone number has already existed", HttpStatus.FORBIDDEN);
        }
        Client client = mapper.toClient(dto);
        Client savedClient = clientRepo.save(client);
        ClientDto clientDto = mapper.toClientDto(savedClient);
        return response(clientDto, HttpStatus.CREATED);
    }

    @Override
    public Client validate(Long id) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (!optionalClient.isPresent()) {
            throw new RuntimeException("Client id = " + id + ", not found!");
        }
        Client client = optionalClient.get();
        if (!client.getActive()) {
            throw new RuntimeException("Client id = " + id + ", is inactive!");
        }
        return client;
    }
}
