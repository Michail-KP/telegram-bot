package ru.dexsys.telegrambot.domain.services;

import org.springframework.stereotype.Service;
import ru.dexsys.telegrambot.domain.entities.Questionnaire;
import ru.dexsys.telegrambot.app.clients.telegram.commands.CommandType;
import ru.dexsys.telegrambot.services.IQuestionnaireService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionnaireService implements IQuestionnaireService {
    private final List<Questionnaire> questionnaires = new ArrayList<>();

    @PostConstruct
    public void init() {
        LinkedList<Questionnaire.Question> questions = new LinkedList<>();
        questions.add(Questionnaire.Question.builder().ident("first").question("Please, enter your birth date:").build());

        this.questionnaires.add(Questionnaire.builder()
                .command(CommandType.BIRTH_DATE.getCommand())
                .title("We really want to wish you a happy birthday!")
                .questions(questions)
                .finalMessage("Thank you for specifying birth date!")
                .build());

        LinkedList<Questionnaire.Question> questions1 = new LinkedList<>();
        questions.add(Questionnaire.Question.builder().ident("first").question("Please, enter your phone number to login in the system").build());

        this.questionnaires.add(Questionnaire.builder()
                .command(CommandType.PHONE.getCommand())
                .title("We need to make clear that you are in the system")
                .questions(questions1)
                .finalMessage("Thank you for specifying phone number!")
                .build());

    }

    public Questionnaire getQuestionnaireByCommand(String command) {
        return this.questionnaires.stream().filter(questionnaire -> questionnaire.getCommand().equals(command)).findFirst().orElse(null);
    }
}
