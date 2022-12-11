package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.GenericTransformer;

@Slf4j
public class FruitRaceChanger implements GenericTransformer<Fruit, Fruit> {

    @Override
    public Fruit transform(Fruit source) {
        log.info("Transforming {} into banana !", source.getRace());
        return source.withRace(Race.BANANA);
    }

}
