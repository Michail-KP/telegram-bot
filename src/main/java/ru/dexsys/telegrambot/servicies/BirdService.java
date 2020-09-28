package ru.dexsys.telegrambot.servicies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.dexsys.telegrambot.models.Bird;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class BirdService {
    private final List<Bird> birdList;

    public ArrayList<String> makeThemFly() {
        ArrayList<String> flights = new ArrayList<>();
        for (Bird bird: birdList) {
            try {
                flights.add(bird.fly());
            } catch (Exception exception) {
                log.error(exception.getMessage());
            }
        }
        return flights;
    }

    public ArrayList<String> getBirdsInfo() {
        return (ArrayList<String>) birdList.stream().map(Object::toString).collect(Collectors.toList());
    }
}
