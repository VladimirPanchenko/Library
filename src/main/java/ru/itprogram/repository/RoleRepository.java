package ru.itprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itprogram.domain.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
