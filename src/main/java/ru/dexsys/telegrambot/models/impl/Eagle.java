package ru.dexsys.telegrambot.models.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.dexsys.telegrambot.models.Bird;

@EqualsAndHashCode(callSuper = true)
@Data
public class Eagle extends Bird {
    private int rangOfFlight;
    private boolean isSwimming;

    public String fly() {
        return "I can fly!";
    }
}
