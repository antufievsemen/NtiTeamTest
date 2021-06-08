package ru.ntiteam.test.antufievsemen.entity;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.entity.Planet;

public class LordTest {

    @Test
    public void createLordTest() {
        Lord lord = new Lord("Max", 20L);
        Assertions.assertEquals("Max", lord.getName());
        Assertions.assertEquals(Long.valueOf(20), lord.getYears());
    }

    @Test
    public void setterLordTest() {
        Lord lord = new Lord();
        lord.setId(1L);
        lord.setName("Max");
        lord.setYears(16L);
        Assertions.assertEquals(Long.valueOf(1), lord.getId());
        Assertions.assertEquals("Max", lord.getName());
        Assertions.assertEquals(Long.valueOf(16), lord.getYears());
    }
}
