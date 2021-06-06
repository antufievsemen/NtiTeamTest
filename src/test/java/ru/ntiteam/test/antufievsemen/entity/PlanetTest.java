package ru.ntiteam.test.antufievsemen.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ntiteam.antufievsemen.entity.Planet;

public class PlanetTest {

    @Test
    public void createPlanet() {
        Planet planet = new Planet(1L, "Mars");
        Assertions.assertEquals(Long.valueOf(1L), planet.getId());
        Assertions.assertEquals("Mars", planet.getName());
    }

    @Test
    public void setterPlanet() {
        Planet planet = new Planet();
        planet.setId(1L);
        planet.setName("Mars");
        Assertions.assertEquals(Long.valueOf(1L), planet.getId());
        Assertions.assertEquals("Mars", planet.getName());
    }
}
