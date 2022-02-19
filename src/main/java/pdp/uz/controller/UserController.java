package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.UserAddDto;
import pdp.uz.model.UserDto;
import pdp.uz.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<UserDto>> add(@RequestBody UserAddDto dto) {
        return userService.add(dto);
    }
}
