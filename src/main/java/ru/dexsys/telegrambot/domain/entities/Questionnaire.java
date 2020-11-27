package ru.dexsys.telegrambot.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;

@Builder
@Getter
public class Questionnaire {
    private final String command;
    private final String title;
    private final LinkedList<Question> questions;
    private final String finalMessage;

    @Builder
    @Getter
    public static class Question {
        private final String ident;
        private final String question;
    }

    //TODO: move to service!
    public Question getNextQuestionByIdent(String ident) {
        Question q = questions.stream().filter(question -> question.getIdent().equals(ident)).findFirst().orElse(null);
        if (q == null) {
            return questions.get(0);
        }

        var itr = questions.listIterator(questions.indexOf(q) + 1);

        return itr.hasNext() ? itr.next() : null;
    }

    public Questionnaire.Question getFirstQuestion() {
        return questions.getFirst();
    }
}
