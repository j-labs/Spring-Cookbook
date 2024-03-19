package pl.jlabs.example.controllers;

import jakarta.validation.Valid;
import pl.jlabs.example.dto.UserDto;
import pl.jlabs.example.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("by-company-name/{companyName}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDto> getUsersByCompanyName(@PathVariable String companyName) {
        return userService.getAllUsersByCompanyName(companyName);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable int userId, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }
}
