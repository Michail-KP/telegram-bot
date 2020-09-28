package ru.dexsys.telegrambot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dexsys.telegrambot.models.Bird;
import ru.dexsys.telegrambot.models.impl.Eagle;
import ru.dexsys.telegrambot.models.impl.Hummingbird;
import ru.dexsys.telegrambot.models.impl.Ostrich;
import ru.dexsys.telegrambot.models.impl.Raven;
import ru.dexsys.telegrambot.servicies.BirdService;

import java.util.ArrayList;

@Configuration
public class BirdConfig {
    @Bean(initMethod = "init")
    public Eagle eagle() {
        Eagle eagle = new Eagle();
        eagle.setRangOfFlight(40);
        eagle.setSwimming(false);
        return eagle;
    }

    @Bean(initMethod = "init")
    public Hummingbird hummingbird() {
        return Hummingbird.builder().isSwimming(true).rangOfFlight(43).build();
    }

    @Bean(initMethod = "init")
    public Ostrich ostrich() {
        return new Ostrich(50, true);
    }

    @Bean(initMethod = "init")
    public Raven raven() {
        return new Raven();
    }

    @Bean
    public BirdService birdService() {
        ArrayList<Bird> birds = new ArrayList<>();
        birds.add(eagle());
        birds.add(hummingbird());
        birds.add(ostrich());
        birds.add(raven());
        return new BirdService(birds);
    }
}
