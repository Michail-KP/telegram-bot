package ru.dexsys.telegrambot.app.clients.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dexsys.telegrambot.app.clients.telegram.commands.Result;
import ru.dexsys.telegrambot.domain.services.CommandService;
import ru.dexsys.telegrambot.services.ITelegramHandler;

public class TelegramHandler extends TelegramLongPollingBot implements ITelegramHandler {
    @Autowired
    private CommandService commandService;

    @Value("${bot.username}")
    private String userName;

    @Value("${bot.token}")
    private String token;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        var user = update.getMessage().getFrom();
        Result<?> result;
        if (update.getMessage().isCommand()) {
            var cmd = update.getMessage().getText();
            result = commandService.getCommand(user, cmd).run(null);
        } else {
            var userData = update.getMessage().getText();
            result = commandService.getActiveUserCommand(user).run(userData);
        }

       execute(result.getObjectToSend());
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}