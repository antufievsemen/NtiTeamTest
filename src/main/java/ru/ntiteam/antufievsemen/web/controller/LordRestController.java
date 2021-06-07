package ru.ntiteam.antufievsemen.web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.service.LordService;

@RestController
@RequestMapping("/lord")
public class LordRestController {

    private final LordService lordService;

    public LordRestController(LordService lordService) {
        this.lordService = lordService;
    }

    @GetMapping("/{id}")
    public Lord getLordById(@PathVariable Long id) {
        return lordService.getLordById(id).orElseGet( () -> null);
    }

    @GetMapping("/uselessLords")
    public List<Lord> getUselessLords() {
        return lordService.getListUselessLords();
    }

    @GetMapping("/topTenYoungestLords")
    public List<Lord> getTopTenYoungestLords() {
        return lordService.getTopTenYoungestLords();
    }

    @GetMapping
    public List<Lord> getLords() {
        return lordService.getLords();
    }

    @PostMapping
    public Lord addLord(@RequestBody Lord lord) {
        return lordService.addLord(lord);
    }

    @DeleteMapping("/{id}")
    public boolean deleteLordById(@PathVariable Long id) {
        return lordService.deleteLordById(id);
    }

    @PutMapping("/{id}")
    public Lord updateLord(@RequestBody Lord lord, @PathVariable Long id) {
        return lordService.updateLord(lord);
    }


}
