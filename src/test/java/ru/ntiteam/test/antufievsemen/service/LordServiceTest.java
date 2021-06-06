package ru.ntiteam.test.antufievsemen.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ntiteam.antufievsemen.service.LordService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LordServiceTest {

    @Autowired
    private LordService lordService;

    @Test
    public void addLordTest() {

    }
}
