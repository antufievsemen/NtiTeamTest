package ru.ntiteam.antufievsemen.web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.service.PlanetService;

@RestController
@RequestMapping("/planet")
public class PlanetRestController {

    private final PlanetService planetService;

    public PlanetRestController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/{id}")
    public Planet getPlanetById(@PathVariable Long id) {
        return planetService.getPlanetById(id).orElseGet( () -> null);
    }

    @GetMapping
    public List<Planet> getPlanets() {
        return planetService.getPlanets();
    }

    @PostMapping
    public Planet addPlanet(@RequestBody Planet planet) {
        return planetService.addPlanet(planet);
    }

    @DeleteMapping("/{id}")
    public boolean deletePlanetById(@PathVariable Long id) {
        return planetService.deletePlanetById(id);
    }

    @PutMapping("/{id}")
    public Planet updatePlanet(@RequestBody Planet planet, @PathVariable Long id) {
        return planetService.updatePlanet(planet);
    }
}
