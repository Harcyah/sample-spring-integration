package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.GenericTransformer;

import java.awt.Color;

@Slf4j
public class BananaConverter implements GenericTransformer<Fruit, Fruit> {

    @Override
    public Fruit transform(Fruit source) {
        return source
            .withRace(Race.BANANA)
            .withColor(Color.YELLOW)
            .withAngle(30.0f);
    }

}
