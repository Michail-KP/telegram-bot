package ru.dexsys.telegrambot.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.dexsys.telegrambot.domain.entities.TelegramUser;
import ru.dexsys.telegrambot.domain.repositories.TelegramUserRepository;
import ru.dexsys.telegrambot.services.ITelegramUserService;

import java.sql.Date;

@Service
public class TelegramUserService implements ITelegramUserService {
    @Autowired
    private TelegramUserRepository telegramUserRepository;

    public TelegramUser createOrUpdate(User user) {
        var telegramUser = getUserByChatId(user.getId().longValue());
        if (telegramUser != null) {
            telegramUser.setFirstName(user.getFirstName());
            telegramUser.setLastName(user.getLastName());
            telegramUser.setUserName(user.getUserName());
        } else {
            telegramUser = new TelegramUser();
            telegramUser.setId(user.getId().longValue());
            telegramUser.setUserName(user.getUserName());
            telegramUser.setFirstName(user.getFirstName());
            telegramUser.setLastName(user.getLastName());
        }

        // TODO: may add such fields like phone and else
        return telegramUserRepository.save(telegramUser);
    }

    public void updateTelegramUser(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    public TelegramUser getUserByChatId(Long chatId) {
        return telegramUserRepository.findById(chatId).orElse(null);
    }

    public void updateUserState(Long tgUserId, String command, String questionIdent) {
        var tgUser = getUserByChatId(tgUserId);
        tgUser.setCommand(command);
        tgUser.setQuestionIdent(questionIdent);
        telegramUserRepository.save(tgUser);
    }

    public void resetUserState(Long tgUserId) {
        var tgUser = getUserByChatId(tgUserId);
        tgUser.setCommand(null);
        tgUser.setQuestionIdent(null);
        telegramUserRepository.save(tgUser);
    }

    public void setBirthDate(Long id, Date date) {
        TelegramUser tgUser = getUserByChatId(id);
        tgUser.setBirthDate(date);
        updateTelegramUser(tgUser);
    }

    public void setPhone(Long id, String phone) {
        TelegramUser tgUser = getUserByChatId(id);
        tgUser.setPhone(phone);
        updateTelegramUser(tgUser);
    }
}
