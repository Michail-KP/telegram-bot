package ru.dexsys.telegrambot.models.impl;

import lombok.AllArgsConstructor;
import lombok.ToString;
import ru.dexsys.telegrambot.models.Bird;

@AllArgsConstructor
@ToString
public class Ostrich extends Bird {
    private int rangOfFlight;
    private boolean isSwimming;

    @Override
    public String fly() throws Exception {
        throw new Exception("I'm " + this.getClass().getSimpleName() + ". I believe, I can fly...");
    }
}
