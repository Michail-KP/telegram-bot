package ru.dexsys.telegrambot.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.dexsys.telegrambot.app.clients.telegram.commands.CommandType;
import ru.dexsys.telegrambot.services.ICommand;
import ru.dexsys.telegrambot.app.exceptions.CommandNotFoundException;
import ru.dexsys.telegrambot.services.ICommandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandService implements ICommandService {
    @Autowired
    private List<ICommand> commands;
    @Autowired
    private TelegramUserService telegramUserService;

    public List<CommandType> getActiveCommandTypes() {
        return commands.stream().map(ICommand::getCommandType).collect(Collectors.toList());
    }

    public ICommand getCommand(User user, String tgMessage) {
        var command = commands.stream()
                .filter(c -> c.getCommandType().getCommand().equals(tgMessage))
                .findFirst()
                .orElse(getHelpCommand());
        command.setUser(user);
        return command;
    }

    public ICommand getActiveUserCommand(User user) throws CommandNotFoundException {
        var tgUser = telegramUserService.getUserByChatId(user.getId().longValue());
        if (tgUser.getCommand() == null) {
            var helpCommand = getHelpCommand();
            helpCommand.setUser(user);
            return helpCommand;
        }

        var command = commands.stream()
                .filter(cmd -> cmd.getCommandType().getCommand().equals(tgUser.getCommand()))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(tgUser.getCommand() + " command doesn't registered"));
        command.setUser(user);
        command.setQuestionIdent(tgUser.getQuestionIdent());
        return command;
    }

    private ICommand getHelpCommand() throws CommandNotFoundException {
        return commands.stream()
                .filter(cmd -> cmd.getCommandType().equals(CommandType.HELP))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException("Cannot find HelpCommand"));
    }
}
