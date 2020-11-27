package ru.dexsys.telegrambot.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dexsys.telegrambot.domain.entities.TelegramUser;

public interface TelegramUserRepository extends CrudRepository<TelegramUser, Long> {

}
