package ru.ntiteam.test.antufievsemen.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.PlanetRepository;
import ru.ntiteam.antufievsemen.service.PlanetService;
import ru.ntiteam.test.antufievsemen.config.ConfigurationTest;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigurationTest.class,
        loader = AnnotationConfigContextLoader.class)
public class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;

    private static final Planet planet = new Planet(1L, "Mars", null);

    @Test
    public void addPlanetTest() {

    }
}
