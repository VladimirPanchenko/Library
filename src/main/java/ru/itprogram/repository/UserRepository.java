package ru.itprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itprogram.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
