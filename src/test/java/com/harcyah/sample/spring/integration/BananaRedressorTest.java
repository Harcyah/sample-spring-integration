package com.harcyah.sample.spring.integration;

import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.Color;
import java.util.UUID;

import static com.harcyah.sample.spring.integration.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
public class BananaRedressorTest {

    @Autowired
    private MessageChannel fruitSourceChannel;

    @Autowired
    private QueueChannel successChannel;

    @BeforeEach
    public void before() {
        successChannel.clear();
    }

    @AfterEach
    public void after() {
        successChannel.clear();
    }

    @Test
    public void testWithMalformedBanana() {
        // Given
        Fruit fruit = new Fruit(UUID.randomUUID(), Race.BANANA, Color.RED, 0.f);
        Message<Fruit> message = MessageBuilder.withPayload(fruit).build();

        // When
        fruitSourceChannel.send(message);

        // Then
        Fruit actualFruit = (Fruit) successChannel.receive().getPayload();
        assertThat(actualFruit)
            .hasRace(Race.BANANA)
            .hasColor(Color.YELLOW)
            .hasAngle(30.f);
    }

    @Test
    public void testWithKiwi() {
        // Given
        Fruit fruit = new Fruit(UUID.randomUUID(), Race.KIWI, Color.RED, 0.f);
        Message<Fruit> message = MessageBuilder.withPayload(fruit).build();

        // When
        fruitSourceChannel.send(message);

        // Then
        assertThat(successChannel.getQueueSize()).isEqualTo(0);
    }

}
