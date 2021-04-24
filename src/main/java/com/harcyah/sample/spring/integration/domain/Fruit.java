package com.harcyah.sample.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

import java.awt.Color;
import java.util.UUID;

@With
@Getter
@Builder
@AllArgsConstructor
public class Fruit {

    private final UUID id;
    private final Race race;
    private final Color color;
    private final double angle;

    @Override
    public String toString() {
        return String.format("Fruit [%s] [%s] [%d,%d,%d] %f°",
            id.toString(),
            race,
            color.getRed(), color.getGreen(), color.getBlue(),
            angle
        );
    }

}
