package ru.dexsys.telegrambot.services;

import org.telegram.telegrambots.meta.api.objects.User;
import ru.dexsys.telegrambot.domain.entities.TelegramUser;

import java.sql.Date;

public interface ITelegramUserService {
    TelegramUser createOrUpdate(User user);

    void updateTelegramUser(TelegramUser telegramUser);

    TelegramUser getUserByChatId(Long chatId);

    void updateUserState(Long tgUserId, String command, String questionIdent);

    void resetUserState(Long tgUserId);

    void setBirthDate(Long id, Date date);
}
