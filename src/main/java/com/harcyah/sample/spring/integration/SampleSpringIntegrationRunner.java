package com.harcyah.sample.spring.integration;

import com.google.common.util.concurrent.Uninterruptibles;
import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.awt.Color;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class SampleSpringIntegrationRunner implements CommandLineRunner {

    private static final Random random = new Random();

    private static final Color[] COLORS = {
        Color.YELLOW,
        Color.GREEN,
        Color.PINK,
        Color.BLUE,
    };

    private final FruitSourceNotifier notifier;

    @Override
    public void run(String... args) {
        for (int i = 0; i < 100; i++) {
            Race race = Race.values()[random.nextInt(Race.values().length)];
            Color color = COLORS[random.nextInt(COLORS.length)];
            Float angle = new Random().nextFloat();
            Fruit fruit = new Fruit(UUID.randomUUID(), race, color, angle);
            notifier.onNewFruit(fruit);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
    }

}
