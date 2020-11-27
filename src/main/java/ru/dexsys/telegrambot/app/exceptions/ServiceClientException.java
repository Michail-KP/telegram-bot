package ru.dexsys.telegrambot.app.exceptions;

public class ServiceClientException extends RuntimeException {
    public ServiceClientException(String message) {
        super(message);
    }
}
