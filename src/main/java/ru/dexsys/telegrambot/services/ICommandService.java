package ru.dexsys.telegrambot.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.dexsys.telegrambot.app.clients.telegram.commands.CommandType;
import ru.dexsys.telegrambot.app.exceptions.CommandNotFoundException;
import ru.dexsys.telegrambot.domain.services.TelegramUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface ICommandService {
    List<CommandType> getActiveCommandTypes();

    ICommand getCommand(User user, String tgMessage);

    ICommand getActiveUserCommand(User user) throws CommandNotFoundException;
}
