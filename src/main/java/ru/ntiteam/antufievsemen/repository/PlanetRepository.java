package ru.ntiteam.antufievsemen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntiteam.antufievsemen.entity.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
