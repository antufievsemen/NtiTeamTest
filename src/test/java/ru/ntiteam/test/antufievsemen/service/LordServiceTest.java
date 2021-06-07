package ru.ntiteam.test.antufievsemen.service;

import com.sun.source.tree.ModuleTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.LordRepository;
import ru.ntiteam.antufievsemen.service.LordService;

@SpringBootTest(classes = LordService.class)
@ExtendWith(MockitoExtension.class)
public class LordServiceTest {

    @Autowired
    private LordService lordService;

    @MockBean
    private LordRepository lordRepository;

    @Test
    public void addLordTest() {
        Lord lord = new Lord(1L, "Mil", 12L, null);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenReturn(lord);
        Lord actualLord = lordService.addLord(lord);
        Assertions.assertEquals(lord, actualLord);
    }

    @Test
    public void addLordThrowExceptionTest() {
        Lord lord = new Lord(1L, "Mil", 12L, null);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenThrow(new IllegalArgumentException("rollback"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> lordService.addLord(lord));
    }

    @Test
    public void getLordByIdTest() {
        Lord lord = new Lord(1L, "Mil", 12L, null);
        Optional<Lord> optionalLord = Optional.of(lord);
        Mockito.when(lordRepository.findById(Mockito.eq(1L))).thenReturn(optionalLord);
        Optional<Lord> actualLordOptional = lordService.getLordById(1L);
        Assertions.assertTrue(actualLordOptional.isPresent());
        Assertions.assertEquals(lord, actualLordOptional.get());
    }

    @Test
    public void getLordByIdNullTest() {
        Optional<Lord> optionalLord = Optional.empty();
        Mockito.when(lordRepository.findById(Mockito.any())).thenReturn(optionalLord);
        Optional<Lord> actualLordOptional = lordService.getLordById(1L);
        Assertions.assertTrue(actualLordOptional.isEmpty());
    }

    @Test
    public void deleteLordByIdTest() {
        Lord lord = new Lord(1L, "Mil", 12L, null);
        Optional<Lord> optionalLord = Optional.of(lord);
        Mockito.when(lordRepository.findById(Mockito.eq(1L))).thenReturn(optionalLord);
        Mockito.doNothing().when(lordRepository).deleteById(Mockito.eq(1L));
        Assertions.assertTrue(lordService.deleteLordById(1L));
    }

    @Test
    public void deleteLordByIdNullTest() {
        Optional<Lord> optionalLord = Optional.empty();
        Mockito.when(lordRepository.findById(Mockito.any())).thenReturn(optionalLord);
        Mockito.doNothing().when(lordRepository).deleteById(Mockito.eq(1L));
        Assertions.assertFalse(lordService.deleteLordById(1L));
    }

    @Test
    public void updateLordTest() {
        Lord lord = new Lord(1L, "Mil", 12L, null);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenReturn(lord);
        Lord actualLord = lordService.updateLord(lord);
        Assertions.assertEquals(lord, actualLord);
    }

    @Test
    public void updateLordThrowExceptionTest() {
        Lord lord = new Lord(1L, "Mil", 12L, null);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenThrow(new IllegalArgumentException("rollback"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> lordService.updateLord(lord));
    }

    @Test
    public void getLordsTest() {
        Lord lord1 = new Lord(1L, "Mil", 12L, null);
        Lord lord2 = new Lord(2L, "Did", 300L, null);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord1);
        lordList.add(lord2);
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        List<Lord> actualLords = lordService.getLords();
        Assertions.assertEquals(lordList, actualLords);
    }

    @Test
    public void getLordsNullTest() {
        Mockito.when(lordRepository.findAll()).thenReturn(null);
        List<Lord> actualLords = lordService.getLords();
        Assertions.assertNull(actualLords);
    }

    @Test
    public void getUselessLordsTest () {
        Planet planet = new Planet("Mars", null);
        Set<Planet> planets = new HashSet<>();
        planets.add(planet);
        Lord lord1 = new Lord(1L, "Mil", 12L, planets);
        Lord lord2 = new Lord(2L, "Did", 300L, null);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord2);
        lordList.add(lord1);
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        List<Lord> actualUselessLords = lordService.getListUselessLords();
        Assertions.assertEquals(List.of(lord2), actualUselessLords);
    }

    @Test
    public void getUselessLordsEmptyTest() {
        Planet planet1 = new Planet("Mars", null);
        Planet planet2 = new Planet("Earth", null);
        Set<Planet> planetsLord1 = new HashSet<>();
        planetsLord1.add(planet1);
        Set<Planet> planetsLord2 = new HashSet<>();
        planetsLord2.add(planet2);
        Lord lord1 = new Lord(1L, "Mil", 12L, planetsLord1);
        Lord lord2 = new Lord(2L, "Did", 300L, planetsLord2);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord1);
        lordList.add(lord2);
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        List<Lord> actualUselessLords = lordService.getListUselessLords();
        Assertions.assertEquals(Collections.emptyList(), actualUselessLords);
    }

    @Test
    public void getTopTenYoungestLordsTest() {
        Lord lord1 = new Lord(1L, "Mil", 12L, null);
        Lord lord2 = new Lord(2L, "Did", 300L, null);
        Lord lord3 = new Lord(2L, "Kan", 3150L, null);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord3);
        lordList.add(lord2);
        lordList.add(lord1);
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        List<Lord> actualTopTenYoungestLords = lordService.getTopTenYoungestLords();
        Assertions.assertEquals(List.of(lord1, lord2, lord3), actualTopTenYoungestLords);
    }
}
