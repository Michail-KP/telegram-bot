package ru.dexsys.telegrambot.app.clients.telegram.commands;

public enum CommandType {
    START("/start"),
    INFO("/info"),
    BIRTH_DATE("/birthdate"),
    HELP("/help"),
    PHONE("/phone");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
