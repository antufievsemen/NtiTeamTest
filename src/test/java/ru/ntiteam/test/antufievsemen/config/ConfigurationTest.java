package ru.ntiteam.test.antufievsemen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ntiteam.antufievsemen.service.PlanetService;

import static org.mockito.Mockito.mock;

@Configuration
public class ConfigurationTest {

    @Bean
    public PlanetService planetService() {
        return mock(PlanetService.class);
    };
}
