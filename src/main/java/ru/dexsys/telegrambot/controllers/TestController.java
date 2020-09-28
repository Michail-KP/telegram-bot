package ru.dexsys.telegrambot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dexsys.telegrambot.servicies.BirdService;
import java.util.ArrayList;

@Slf4j
@RestController
public class TestController {
    private final BirdService birdService;

    @Autowired
    public TestController(BirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("/makeThemFly")
    public String makeThemFly() {
        ArrayList<String> flights = birdService.makeThemFly();
        return flights.toString();
    }

    @GetMapping("/getBirdsInfo")
    public String getBirdsInfo() {
        ArrayList<String> infoList = birdService.getBirdsInfo();
        return infoList.toString();
    }
}
