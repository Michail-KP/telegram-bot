package ru.dexsys.telegrambot.app.clients.mockServer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Date birthDay;
    private String chatId;
    private String firstName;
    private String id;
    private boolean male;
    private String middleName;
    private String phone;
    private String secondName;
}
