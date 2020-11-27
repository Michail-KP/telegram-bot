package ru.dexsys.telegrambot.app.clients.telegram.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.dexsys.telegrambot.app.clients.mockServer.MockServerClient;
import ru.dexsys.telegrambot.domain.services.TelegramUserService;

@Component
public class StartCommand extends AbstractCommand {
    @Getter
    public final CommandType commandType = CommandType.START;
    @Autowired
    private TelegramUserService telegramUserService;
    @Autowired
    private MockServerClient mockServerClient;

//    public boolean middleware() {
//        return true;
//    }

    @Override
    public SendMessage process() {
        var user = telegramUserService.createOrUpdate(getUser());
        if (user != null) {
            var msg = "Hello, my dear friend! Let's get acquainted - my name is WowBirthdayBot and I'll remind your colleagues about your birthday! I also have some additional commands. To see them, please use /help.";
            if (user.getPhone() == null) {
                msg += "please, enter /phone command to login in the system";
            }

            return buildMessage(msg);
        }

        return buildMessage("Something went wrong ...");
    }

    @Override
    public SendMessage process(String userData) {
        return null;
    }
}
