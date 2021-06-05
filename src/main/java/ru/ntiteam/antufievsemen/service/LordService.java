package ru.ntiteam.antufievsemen.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.repository.LordRepository;

@Service
public class LordService {

    private final LordRepository lordRepository;


    public LordService(LordRepository lordRepository) {
        this.lordRepository = lordRepository;
    }

    public Optional<Lord> getLordById(Long id) {
        return lordRepository.findById(id);
    }

    public List<Lord> getLords() {
        return lordRepository.findAll();
    }

    public Lord addLord(Lord lord) {
        return lordRepository.saveAndFlush(lord);
    }

    public boolean deleteLordById(Long id) {
        Optional<Lord> optionalLord = getLordById(id);
        if (optionalLord.isPresent()) {
            lordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Lord updateLord(Lord lord) {
        return lordRepository.saveAndFlush(lord);
    }
}
