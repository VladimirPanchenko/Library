package ru.itprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itprogram.domain.entity.ReaderEntity;

public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {
}
