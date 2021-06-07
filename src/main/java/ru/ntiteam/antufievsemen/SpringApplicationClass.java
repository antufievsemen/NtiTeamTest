package ru.ntiteam.antufievsemen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.service.LordService;
import ru.ntiteam.antufievsemen.service.PlanetService;

@SpringBootApplication
@EnableTransactionManagement
public class SpringApplicationClass {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationClass.class, args);
    }

    @Bean
    public boolean setValues(LordService lordService, PlanetService planetService) {
        for (int i = 0; i < 10; i++) {
            lordService.addLord(new Lord(String.valueOf(i), i * 10L, null));
        }
        for (int i = 0; i < 10; i++) {
            if (i > 6) {
              planetService.addPlanet(new Planet(String.valueOf(i *3), lordService.getLordById((long) i).get()));
            } else {
                planetService.addPlanet(new Planet(String.valueOf(i * 3), null));
            }
        }
        return true;
    }
}
