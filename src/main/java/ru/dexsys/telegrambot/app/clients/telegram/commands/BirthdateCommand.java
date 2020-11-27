package ru.dexsys.telegrambot.app.clients.telegram.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.dexsys.telegrambot.domain.entities.Questionnaire;
import ru.dexsys.telegrambot.app.clients.mockServer.MockServerClient;
import ru.dexsys.telegrambot.domain.services.QuestionnaireService;
import ru.dexsys.telegrambot.domain.services.TelegramUserService;
import ru.dexsys.telegrambot.domain.utils.DateUtil;

@Component
public class BirthdateCommand extends AbstractCommand {
    @Getter
    private final CommandType commandType = CommandType.BIRTH_DATE;

    @Autowired
    private MockServerClient mockServerClient;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private TelegramUserService telegramUserService;

    @Override
    public boolean middleware() {
        return mockServerClient.isUserAllowed(getUser().getId().longValue());
    }

    @Override
    public BotApiMethod<?> process() {
        var questionnaire = questionnaireService.getQuestionnaireByCommand(commandType.getCommand());
        Questionnaire.Question question = questionnaire.getFirstQuestion();
        setQuestionIdent(question.getIdent());
        return buildMessage(questionnaire.getTitle() + "\n" + question.getQuestion());
    }

    @Override
    public BotApiMethod<?> process(String userData) {
        //TODO: wrap to closure
        var currentQuestionIdent = getQuestionIdent();
        if (!validate(userData, currentQuestionIdent)) {
            return buildMessage("Please, enter the correct date");
        }

        switch (currentQuestionIdent) {
            case "first":
                var date = DateUtil.prepareDate(userData);
                telegramUserService.setBirthDate(getUser().getId().longValue(), date);
        }

        var questionnaire = questionnaireService.getQuestionnaireByCommand(commandType.getCommand());
        var nextQuestion = questionnaire.getNextQuestionByIdent(currentQuestionIdent);
        if (nextQuestion == null) {
            setQuestionIdent(null);
            return buildMessage(questionnaire.getFinalMessage());
        }
        
        return buildMessage(nextQuestion.getQuestion());
    }

    public boolean validate(String userData, String questionIdent) {
        return true;
    }
}
