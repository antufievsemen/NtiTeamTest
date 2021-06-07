package ru.ntiteam.antufievsemen.service;

import java.util.ArrayList;
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

    @Transactional
    public List<Lord> getListUselessLords() {
        List<Lord> lords = getLords();
        List<Lord> resultList = new ArrayList<>();
        for (Lord lord : lords) {
            if (lord.getPlanets() == null) {
                resultList.add(lord);
            }
        }
        return resultList;
    }

    @Transactional
    public List<Lord> getTopTenYoungestLords() {
        List<Lord> lords = getLords();
        List<Lord> resultList = new ArrayList<>();
        lords.sort((o1, o2) -> {
            if (o1.getYears() > o2.getYears()) {
                return 1;
            } else {
                if (o1.getYears().equals(o2.getYears())) {
                    return 0;
                }
            }
            return -1;
        });
        for (int i = 0; i < lords.size(); i++) {
            resultList.add(i, lords.get(i));
        }
        return resultList;
    }
}
