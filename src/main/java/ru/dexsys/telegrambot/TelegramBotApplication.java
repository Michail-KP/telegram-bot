package ru.dexsys.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TelegramBotApplication {
    public static void main(String[] args) {
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//
//        try {
//            telegramBotsApi.registerBot(new BirthdayBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        var ctx = SpringApplication.run(TelegramBotApplication.class, args);
//        ApiContextInitializer.init();

        SpringApplication.run(TelegramBotApplication.class, args);
    }
}
