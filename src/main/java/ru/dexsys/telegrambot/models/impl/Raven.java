package ru.dexsys.telegrambot.models.impl;

import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dexsys.telegrambot.models.Bird;

@NoArgsConstructor
@ToString
public class Raven extends Bird {

    @Override
    public String fly(){
        return "Yhoooooo!";
    }
}
