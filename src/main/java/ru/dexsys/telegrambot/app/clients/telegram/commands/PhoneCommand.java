package ru.dexsys.telegrambot.app.clients.telegram.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.dexsys.telegrambot.app.clients.mockServer.dtos.UserDto;
import ru.dexsys.telegrambot.domain.entities.Questionnaire;
import ru.dexsys.telegrambot.domain.services.MockServerService;
import ru.dexsys.telegrambot.domain.services.QuestionnaireService;
import ru.dexsys.telegrambot.domain.utils.DateUtil;

public class PhoneCommand extends AbstractCommand {
    @Getter
    public final CommandType commandType = CommandType.PHONE;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private MockServerService mockServerService;

    @Override
    public BotApiMethod<?> process() {
        var questionnaire = questionnaireService.getQuestionnaireByCommand(commandType.getCommand());
        Questionnaire.Question question = questionnaire.getFirstQuestion();
        setQuestionIdent(question.getIdent());
        return buildMessage(questionnaire.getTitle() + "\n" + question.getQuestion());
    }

    @Override
    public BotApiMethod<?> process(String phone) {
        //TODO: wrap to closure
        var currentQuestionIdent = getQuestionIdent();
        if (!validate(phone)) {
            return buildMessage("Please, enter the correct phone number");
        }

        switch (currentQuestionIdent) {
            case "first":
                telegramUserService.setPhone(getUser().getId().longValue(), phone);
                var users = mockServerService.getUsers();
                var user = users.stream().filter(u -> u.getPhone().equals(phone)).findFirst().get();
//                user.setPhone(p);
//                mockServerService.updateUser(user.);

        }

        var questionnaire = questionnaireService.getQuestionnaireByCommand(commandType.getCommand());
        var nextQuestion = questionnaire.getNextQuestionByIdent(currentQuestionIdent);
        if (nextQuestion == null) {
            setQuestionIdent(null);
            return buildMessage(questionnaire.getFinalMessage());
        }

        return buildMessage(nextQuestion.getQuestion());
    }

    public boolean validate(String phone) {
        return true;
    }
}
