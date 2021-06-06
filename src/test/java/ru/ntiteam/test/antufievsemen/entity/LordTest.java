package ru.ntiteam.test.antufievsemen.entity;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.entity.Planet;

public class LordTest {

    @Test
    public void createLordTest() {
        Planet planet = new Planet(1L, "Mars");
        Set<Planet> setPlanets = new HashSet<>();
        setPlanets.add(planet);
        Lord lord = new Lord(1L, "Max", 20L, setPlanets);
        Assertions.assertEquals(Long.valueOf(1), lord.getId());
        Assertions.assertEquals("Max", lord.getName());
        Assertions.assertEquals(Long.valueOf(20), lord.getYears());
        Assertions.assertTrue(lord.getPlanets().contains(planet));
    }

    @Test
    public void setterLordTest() {
        Planet planet = new Planet(1L, "Mars");
        Set<Planet> setPlanets = new HashSet<>();
        setPlanets.add(planet);
        Lord lord = new Lord();
        lord.setId(1L);
        lord.setName("Max");
        lord.setYears(16L);
        lord.setPlanets(setPlanets);
        Assertions.assertEquals(Long.valueOf(1), lord.getId());
        Assertions.assertEquals("Max", lord.getName());
        Assertions.assertEquals(Long.valueOf(16), lord.getYears());
        Assertions.assertTrue(lord.getPlanets().contains(planet));
    }
}
