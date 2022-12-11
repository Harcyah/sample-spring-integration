package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.GenericTransformer;

@Slf4j
public class BananaTwister implements GenericTransformer<Fruit, Fruit> {

    private static final Float DEFINITIVE_BANANA_ANGLE = 30.0f;

    @Override
    public Fruit transform(Fruit source) {
        Fruit target = source.withAngle(DEFINITIVE_BANANA_ANGLE);
        log.info("Transforming {} into {}", source, target);
        return target;
    }

}
