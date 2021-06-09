package ru.ntiteam.antufievsemen.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanetTest {

    @Test
    public void createPlanet() {
        Lord expectedLord = new Lord("name", 2L);
        Planet planet = new Planet("Mars", expectedLord);
        Assertions.assertEquals("Mars", planet.getName());
        Assertions.assertEquals(expectedLord, planet.getLord());
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
