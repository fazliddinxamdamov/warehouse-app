package pdp.uz.service;

import org.springframework.http.ResponseEntity;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.UserAddDto;
import pdp.uz.model.UserDto;

public interface UserService {

    ResponseEntity<ApiResponse<UserDto>> add(UserAddDto dto);
}
