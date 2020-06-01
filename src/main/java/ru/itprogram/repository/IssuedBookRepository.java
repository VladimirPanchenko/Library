package ru.itprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itprogram.domain.entity.IssuedBookEntity;

public interface IssuedBookRepository extends JpaRepository<IssuedBookEntity, Long> {
}
