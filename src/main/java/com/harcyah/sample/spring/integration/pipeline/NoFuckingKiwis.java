package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.GenericSelector;

@Slf4j
public class NoFuckingKiwis implements GenericSelector<Fruit> {

    @Override
    public boolean accept(Fruit source) {
        if (source.getRace().equals(Race.KIWI)) {
            log.info("Filtering out kiwi");
            return false;
        } else {
            return true;
        }
    }

}
