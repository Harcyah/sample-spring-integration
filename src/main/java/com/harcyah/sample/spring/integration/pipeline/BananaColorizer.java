package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.GenericTransformer;

import java.awt.Color;

@Slf4j
public class BananaColorizer implements GenericTransformer<Fruit, Fruit> {

    @Override
    public Fruit transform(Fruit source) {
        Fruit target = source.withColor(Color.YELLOW);
        log.info("Transforming {} into {}", source, target);
        return target;
    }

}
