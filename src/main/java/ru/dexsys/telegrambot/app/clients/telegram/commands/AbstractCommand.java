package ru.dexsys.telegrambot.app.clients.telegram.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.dexsys.telegrambot.domain.services.CommandService;
import ru.dexsys.telegrambot.domain.services.TelegramUserService;
import ru.dexsys.telegrambot.services.ICommand;

public abstract class AbstractCommand implements ICommand {
    private final static String CONTACT_WITH_ADMINISTRATOR = "Please, contact with administrator by the following contact: ...";
    @Autowired
    protected TelegramUserService telegramUserService;
    @Autowired
    protected CommandService commandService;

    private User user;
    private String questionIdent;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setQuestionIdent(String questionIdent) {
        this.questionIdent = questionIdent;
    }

    public String getQuestionIdent() {
        return questionIdent;
    }

    protected Result<? extends BotApiMethod<?>> getResult(BotApiMethod<?> message) {
        if (message == null) {
            return new Result<>(buildMessage("Something went wrong... " + CONTACT_WITH_ADMINISTRATOR));
        }

        fixState();

        return new Result<>(message);
    }

    private void fixState() {
        //TODO: fix
        if (questionIdent != null) {
            telegramUserService.updateUserState(user.getId().longValue(), this.getCommandType().getCommand(), questionIdent);
        } else {
            telegramUserService.resetUserState(user.getId().longValue());
        }
    }

    public Result<? extends BotApiMethod<?>> run(String userData) {
        if (!middleware()) {
            var msg = buildMessage("Sorry, but you are not in the system! " + CONTACT_WITH_ADMINISTRATOR);
            return getResult(msg);
        }

        BotApiMethod<?> method;
        if (userData == null) {
            method = process();
        } else {
            method = process(userData);
        }

        return getResult(method);
    }

    protected SendMessage buildMessage(String text) {
        return new SendMessage()
                .setChatId(user.getId().longValue())
                .setText(text);
    }

    public boolean middleware() {
        return true;
    }
}
