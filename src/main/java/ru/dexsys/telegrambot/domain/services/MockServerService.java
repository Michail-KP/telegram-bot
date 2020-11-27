package ru.dexsys.telegrambot.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dexsys.telegrambot.app.clients.mockServer.MockServerClient;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UpdateDto;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UserDto;

import java.util.List;
import java.util.UUID;

@Service
public class MockServerService {
    @Autowired
    private MockServerClient mockServerClient;

    public List<UserDto> getUsers() {
        return mockServerClient.getUsers();
    }

    public UUID createUser(UserDto userDto) {
        return mockServerClient.createUser(userDto);
    }

    public UserDto getUser(UUID uuid) {
        return mockServerClient.getUser(uuid);
    }

    public boolean updateUser(UUID userId, UpdateDto updateDto) {
        return mockServerClient.updateUser(userId, updateDto);
    }

    public UserDto generateUser() {
        return mockServerClient.generateUser();
    }
}
