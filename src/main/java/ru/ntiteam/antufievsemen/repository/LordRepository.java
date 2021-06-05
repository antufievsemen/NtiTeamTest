package ru.ntiteam.antufievsemen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntiteam.antufievsemen.entity.Lord;

public interface LordRepository extends JpaRepository<Lord, Long> {
}
