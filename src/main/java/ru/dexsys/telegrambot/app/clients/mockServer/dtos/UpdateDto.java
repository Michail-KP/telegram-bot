package ru.dexsys.telegrambot.app.clients.mockServer.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateDto {
    private Date birthDay;
    private String chatId;
    private String phone;
}
