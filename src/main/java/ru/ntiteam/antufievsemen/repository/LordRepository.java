package ru.ntiteam.antufievsemen.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ntiteam.antufievsemen.entity.Lord;

public interface LordRepository extends JpaRepository<Lord, Long> {
    @Query(nativeQuery = true,
    value = "SELECT * FROM lords l WHERE l.id NOT IN (Select lord_id FROM planets WHERE lord_id IS NOT NULL)")
    List<Lord> getAllUselessLords();
}
