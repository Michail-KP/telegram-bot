package ru.dexsys.telegrambot.domain.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import ru.dexsys.telegrambot.services.ITelegramHandler;
import ru.dexsys.telegrambot.app.clients.telegram.TelegramHandler;

@Configuration
public class DomainConfiguration {
    @Bean
    public ITelegramHandler telegramHandler() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        ITelegramHandler telegramHandler = new TelegramHandler();

        try {
            telegramBotsApi.registerBot((LongPollingBot) telegramHandler);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return telegramHandler;
    }
}
