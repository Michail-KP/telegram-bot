package ru.dexsys.telegrambot.app.clients.telegram.commands;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HelpCommand extends AbstractCommand {
    @Getter
    private final CommandType commandType = CommandType.HELP;

    @Override
    public boolean middleware() {
        return true;
    }

    @Override
    public SendMessage process() {
        List<CommandType> commandTypes = commandService.getActiveCommandTypes();
        String commands = commandTypes.stream().map(CommandType::getCommand).collect(Collectors.joining("\n"));
        SendMessage message = new SendMessage();
        message.setText("Here you can see all commands known to bot. \n " + commands + "\nPlease, use only them not to make bot crazy :)");
        message.setChatId(getUser().getId().longValue());
        return message;
    }

    @Override
    public SendMessage process(String userData) {
        return null;
    }
}
