package ru.dexsys.telegrambot.app.clients.telegram.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.dexsys.telegrambot.domain.services.MockServerService;
import ru.dexsys.telegrambot.domain.services.TelegramUserService;

@Component
public class InfoCommand extends AbstractCommand {
    @Getter
    private final CommandType commandType = CommandType.INFO;

    @Autowired
    private MockServerService mockServerService;

    @Autowired
    private TelegramUserService telegramUserService;

    @Override
    public BotApiMethod<?> process() {
        var users = mockServerService.getUsers();
        var optUser = users.stream().filter(u -> u.getChatId().equals(getUser().getId().longValue())).findFirst();
//        if (optUser.) {

//        }
//        mockServerService.getUser(telegramUserService.getUserByChatId(getUser().getId().longValue()));
        return null;
    }

    @Override
    public BotApiMethod<?> process(String userData) {
        return null;
    }

    @Override
    public boolean middleware() {
        return false;
    }
}
