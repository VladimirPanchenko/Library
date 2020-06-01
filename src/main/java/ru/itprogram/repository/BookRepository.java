package ru.itprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itprogram.domain.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
