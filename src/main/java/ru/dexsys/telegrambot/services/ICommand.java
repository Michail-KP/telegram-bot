package ru.dexsys.telegrambot.services;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.dexsys.telegrambot.app.clients.telegram.commands.Result;

public interface ICommand extends ICommandConfig {
    BotApiMethod<?> process();
    BotApiMethod<?> process(String userData);
    Result<? extends BotApiMethod<?>> run(String userData);
}
