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

    @GetMapping(path = "findall")
    public UserDtoResp getAll(){
        return userService.selectAllDto();
    }

    @GetMapping(path = "findid")
    public UserDto getId(@RequestParam("id") Long id) { return userService.selectIdDto(id); }
}
