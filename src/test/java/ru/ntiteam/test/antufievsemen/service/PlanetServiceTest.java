package ru.ntiteam.test.antufievsemen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.PlanetRepository;
import ru.ntiteam.antufievsemen.service.PlanetService;

@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;

    //mock сущность
    @MockBean
    private PlanetRepository planetRepository;


    @Test
    public void addPlanetTest() {
        Planet planet = new Planet("Mars", null);
        Mockito.when( planetRepository.saveAndFlush(Mockito.eq(planet))).thenReturn(planet);
        Planet actualPlanet = planetService.addPlanet(planet);
        Assertions.assertEquals(actualPlanet, new Planet("Mars", null));
    }

    @Test
    public void addPlanetThrowExceptionTest() {
        Planet planet = new Planet("Mars", null);
        Mockito.when( planetRepository.saveAndFlush(Mockito.eq(planet))).thenThrow(new IllegalArgumentException("rollback"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> planetService.addPlanet(planet));
    }

    @Test
    public void getPlanetByIdTest() {
        Planet planet = new Planet("Mars", null);
        planet.setId(1L);
        Optional<Planet> optionalPlanet = Optional.of(planet);
        Mockito.when(planetRepository.findById(Mockito.eq(planet.getId()))).thenReturn(optionalPlanet);
        Optional<Planet> actualPlanet = planetRepository.findById(1L);
        Assertions.assertTrue(actualPlanet.isPresent());
        Assertions.assertEquals(planet, actualPlanet.get());
    }

    @Test
    public void getNullPlanetByIdTest() {
        Optional<Planet> optionalPlanet = Optional.empty();
        Mockito.when(planetRepository.findById(Mockito.any())).thenReturn(optionalPlanet);
        Optional<Planet> actualPlanet = planetRepository.findById(1L);
        Assertions.assertTrue(actualPlanet.isEmpty());
    }

    @Test
    public void deleteExistedPlanetTest() {
        Planet planet = new Planet("Mars", null);
        Optional<Planet> optionalPlanet = Optional.of(planet);
        planet.setId(1L);
        Mockito.when(planetRepository.findById(Mockito.eq(planet.getId()))).thenReturn(optionalPlanet);
        Mockito.doNothing().when(planetRepository).deleteById(optionalPlanet.get().getId());
        Assertions.assertTrue(planetService.deletePlanetById(1L));
    }

    @Test
    public void deleteRejectedPlanetTest() {
        Planet planet = new Planet("Mars", null);
        Optional<Planet> optionalPlanet = Optional.empty();
        Mockito.when(planetRepository.findById(Mockito.eq(planet.getId()))).thenReturn(optionalPlanet);
        Mockito.doNothing().when(planetRepository).deleteById(Mockito.any());
        Assertions.assertFalse(planetService.deletePlanetById(1L));
    }

    @Test
    public void updatePlanetTest() {
        Planet planet = new Planet("Mars", null);
        Mockito.when( planetRepository.saveAndFlush(Mockito.eq(planet))).thenReturn(planet);
        Planet actualPlanet = planetService.updatePlanet(planet);
        Assertions.assertEquals(actualPlanet, new Planet("Mars", null));
    }

    @Test
    public void updatePlanetThrowExceptionTest() {
        Planet planet = new Planet("Mars", null);
        Mockito.when( planetRepository.saveAndFlush(Mockito.eq(planet))).thenThrow(new IllegalArgumentException("rollback"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> planetService.updatePlanet(planet));
    }

    @Test
    public void getPlanetsTest() {
        Planet planet1 = new Planet("Mars", null);
        Planet planet2 = new Planet("Earth", null);
        List<Planet> planets = new ArrayList<>();
        planets.add(planet1);
        planets.add(planet2);
        Mockito.when(planetRepository.findAll()).thenReturn(planets);
        List<Planet> actualPlanets = planetService.getPlanets();
        Assertions.assertEquals(planets, actualPlanets);
    }

    @Test
    public void getPlanetsEmptyTest() {
        Mockito.when(planetRepository.findAll()).thenReturn(null);
        List<Planet> actualPlanets = planetService.getPlanets();
        Assertions.assertNull(actualPlanets);
    }

}

