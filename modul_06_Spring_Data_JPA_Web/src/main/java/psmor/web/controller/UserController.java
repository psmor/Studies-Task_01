package psmor.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psmor.web.dto.UserDto;
import psmor.web.dto.UserDtoResp;
import psmor.web.service.UserService;

@RestController
@RequestMapping(value = "/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping                          //Вызов: GET /v1/user
    public UserDtoResp getAll(){
        return userService.selectAllDto();
    }

    @GetMapping(path = "id")             //Вызов: GET /v1/user/id?id=1
    public UserDto getId(@RequestParam("id") Long id) {
        return userService.selectIdDto(id);
    }

    @GetMapping(path = "/{id}")           //Вызов: GET /v1/user/1
    public UserDto getPatchId(@PathVariable("id") Long id) {
        return userService.selectIdDto(id);
    }
}
