package ru.dexsys.telegrambot.domain.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UpdateDto;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UserDto;
import ru.dexsys.telegrambot.domain.services.MockServerService;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api")
public class MockServerController {
    @Autowired
    private MockServerService mockServerService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return mockServerService.getUsers();
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@RequestBody UserDto userDto) {
        return mockServerService.createUser(userDto);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") UUID userId) {
        var user = mockServerService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateUser(@PathVariable("userId") UUID userId, @RequestBody UpdateDto updateDto) {
        return mockServerService.updateUser(userId, updateDto);
    }

    @PostMapping("/users/generate")
    @ResponseStatus(HttpStatus.OK)
    public UserDto generateUser() {
        return mockServerService.generateUser();
    }
}