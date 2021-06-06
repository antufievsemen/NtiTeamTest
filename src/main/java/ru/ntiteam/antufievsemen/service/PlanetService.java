package ru.ntiteam.antufievsemen.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.PlanetRepository;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Transactional
    public Optional<Planet> getPlanetById(Long id) {
        return planetRepository.findById(id);
    }

    @Transactional
    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    @Transactional
    public Planet addPlanet(Planet planet) {
        return planetRepository.saveAndFlush(planet);
    }

    @Transactional
    public boolean deletePlanetById(Long id) {
        Optional<Planet> optionalPlanet = getPlanetById(id);
        if (optionalPlanet.isPresent()) {
            planetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Planet updatePlanet(Planet planet) {
        return planetRepository.saveAndFlush(planet);
    }
}
