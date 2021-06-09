package ru.ntiteam.antufievsemen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.repository.LordRepository;

@SpringBootTest(classes = LordService.class)
@ExtendWith(MockitoExtension.class)
public class LordServiceTest {

    @Autowired
    private LordService lordService;

    @MockBean
    private LordRepository lordRepository;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(lordRepository);
    }

    @Test
    public void addLordTest() {
        Lord lord = new Lord("Mil", 12L);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenReturn(lord);
        Lord actualLord = lordService.addLord(lord);
        Assertions.assertEquals(lord, actualLord);
    }

    @Test
    public void addLordThrowExceptionTest() {
        Lord lord = new Lord("Mil", 12L);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenThrow(new IllegalArgumentException("rollback"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> lordService.addLord(lord));
    }

    @Test
    public void getLordByIdTest() {
        Lord lord = new Lord("Mil", 12L);
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
        Lord lord = new Lord( "Mil", 12L);
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
        Lord lord = new Lord("Mil", 12L);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenReturn(lord);
        Lord actualLord = lordService.updateLord(lord);
        Assertions.assertEquals(lord, actualLord);
    }

    @Test
    public void updateLordThrowExceptionTest() {
        Lord lord = new Lord("Mil", 12L);
        Mockito.when(lordRepository.saveAndFlush(lord)).thenThrow(new IllegalArgumentException("rollback"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> lordService.updateLord(lord));
    }

    @Test
    public void getLordsTest() {
        Lord lord1 = new Lord( "Mil", 12L);
        Lord lord2 = new Lord( "Did", 300L);
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
}
