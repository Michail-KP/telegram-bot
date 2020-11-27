package ru.dexsys.telegrambot.app.clients.telegram.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@AllArgsConstructor
@Getter
public class Result<T extends BotApiMethod<?>> {
    private final T objectToSend;
}
