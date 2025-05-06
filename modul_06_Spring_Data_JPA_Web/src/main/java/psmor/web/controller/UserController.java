package psmor.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psmor.web.entity.User;
import psmor.web.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "findall")
    public List<User> getAll(){
        System.out.println("Select * from User");
        return userService.selectAll();
    }

    @GetMapping(path = "findid")
    public User getId(@RequestParam("id") Long id) { return userService.selectId(id); }
}
