package ru.ntiteam.antufievsemen.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.PlanetRepository;

@Service
public class PlanetService {


    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Optional<Planet> getPlanetById(Long id) {
        return planetRepository.findById(id);
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    public Planet addPlanet(Planet planet) {
        return planetRepository.saveAndFlush(planet);
    }

    public boolean deletePlanetById(Long id) {
        Optional<Planet> optionalPlanet = getPlanetById(id);
        if (optionalPlanet.isPresent()) {
            planetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Planet updatePlanet(Planet planet) {
        return planetRepository.saveAndFlush(planet);
    }
}
