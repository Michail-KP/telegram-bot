package ru.dexsys.telegrambot.models;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Bird {
    public abstract String fly() throws Exception;

    public void init() {
        log.info("{} was created!", this.getClass().getSimpleName());
    }
}
