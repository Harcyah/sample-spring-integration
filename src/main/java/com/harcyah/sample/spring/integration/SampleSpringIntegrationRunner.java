package com.harcyah.sample.spring.integration;

import com.google.common.util.concurrent.Uninterruptibles;
import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class SampleSpringIntegrationRunner implements CommandLineRunner {

    private static final int RUNS = 100;
    private static final SecureRandom RANDOM = new SecureRandom();

    private static final Color[] COLORS = {
        Color.YELLOW,
        Color.GREEN,
        Color.PINK,
        Color.BLUE,
    };

    private final FruitSourceNotifier notifier;

    @Override
    public void run(String... args) {
        for (int i = 0; i < RUNS; i++) {
            Race race = Race.values()[RANDOM.nextInt(Race.values().length)];
            Color color = COLORS[RANDOM.nextInt(COLORS.length)];
            double angle = RANDOM.nextDouble();
            Fruit fruit = new Fruit(UUID.randomUUID(), race, color, angle);
            notifier.onNewFruit(fruit);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
    }

}
