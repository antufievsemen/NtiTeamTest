package ru.ntiteam.antufievsemen.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.repository.LordRepository;

@Service
public class LordService {

    private final LordRepository lordRepository;

    public LordService(LordRepository lordRepository) {
        this.lordRepository = lordRepository;
    }

    @Transactional
    public Optional<Lord> getLordById(Long id) {
        return lordRepository.findById(id);
    }

    @Transactional
    public List<Lord> getLords() {
        return lordRepository.findAll();
    }

    @Transactional
    public Lord addLord(Lord lord) {
        return lordRepository.saveAndFlush(lord);
    }

    @Transactional
    public boolean deleteLordById(Long id) {
        Optional<Lord> optionalLord = getLordById(id);
        if (optionalLord.isPresent()) {
            lordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Lord updateLord(Lord lord) {
        return lordRepository.saveAndFlush(lord);
    }
}
