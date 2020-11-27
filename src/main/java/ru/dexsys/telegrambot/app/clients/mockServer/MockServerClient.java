package ru.dexsys.telegrambot.app.clients.mockServer;

import com.sun.research.ws.wadl.HTTPMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UpdateDto;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UserDto;
import ru.dexsys.telegrambot.app.exceptions.ServiceClientException;

import java.util.List;
import java.util.UUID;

@Service
//TODO: add interface
public class MockServerClient {

    @Value("${clients.mock-server.url}")
    private String baseUrl;

    @Autowired
    private RestOperations restTemplate;

    //TODO: rename
    public boolean isUserAllowed(Long chatId) {
        return true;
    }

    public List<UserDto> getUsers() throws ServiceClientException {
        HttpEntity<Object> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<UserDto>> response;
        var url = baseUrl + "/users";
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
        } catch (RestClientException exception) {
            throw new ServiceClientException("Cannot get users");
        }

        return response.getBody();
    }

    public UUID createUser(UserDto userDto) {
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, new HttpHeaders());
        ResponseEntity<UUID> response;
        var url = baseUrl + "/users";
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, UUID.class);
        } catch (RestClientException exception) {
            throw new ServiceClientException("Cannot create user");
        }

        return response.getBody();
    }

    public UserDto getUser(UUID userId) throws ServiceClientException {
        HttpEntity<Object> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<UserDto> response;
        var url = baseUrl + "/users/" + userId;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, UserDto.class);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }

            throw new ServiceClientException("Cannot get user.");
        }

        return response.getBody();
    }

    public Boolean updateUser(UUID userId, UpdateDto updateDto) {
        HttpEntity<UpdateDto> entity = new HttpEntity<>(updateDto, new HttpHeaders());
        ResponseEntity<Boolean> response;
        var url = baseUrl + "/users/" + userId;
        try {
            response = restTemplate.exchange(url, HttpMethod.PATCH, entity, Boolean.class);
        } catch (RestClientException exception) {
            throw new ServiceClientException("Cannot update user");
        }

        return response.getBody();
    }

    public UserDto generateUser() {
        HttpEntity<Object> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<UserDto> response;
        var url = baseUrl + "/users/generate";
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, UserDto.class);
        } catch (RestClientException exception) {
            throw new ServiceClientException("Cannot generate user");
        }

        return response.getBody();
    }
}
