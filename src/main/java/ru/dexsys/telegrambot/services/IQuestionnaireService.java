package ru.dexsys.telegrambot.services;

import ru.dexsys.telegrambot.domain.entities.Questionnaire;

public interface IQuestionnaireService {
    void init();

    Questionnaire getQuestionnaireByCommand(String command);
}

