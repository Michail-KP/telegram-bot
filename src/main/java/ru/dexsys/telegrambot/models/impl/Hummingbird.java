package ru.dexsys.telegrambot.models.impl;

import lombok.Builder;
import lombok.ToString;
import ru.dexsys.telegrambot.models.Bird;

@Builder
@ToString
public class Hummingbird extends Bird {
    private int rangOfFlight;
    private boolean isSwimming;

    public String fly() {
        return "I can fly perfectly!";
    }
}
