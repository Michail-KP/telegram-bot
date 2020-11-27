package ru.dexsys.telegrambot.services;

import org.telegram.telegrambots.meta.api.objects.User;
import ru.dexsys.telegrambot.app.clients.telegram.commands.CommandType;

public interface ICommandConfig {
    boolean middleware();
    CommandType getCommandType();
    //TODO: how to delegate it to lombok?
    void setUser(User user);
    User getUser();
    void setQuestionIdent(String questionIdent);
    String getQuestionIdent();
}
