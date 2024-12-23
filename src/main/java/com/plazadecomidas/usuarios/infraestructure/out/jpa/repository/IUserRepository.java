package com.plazadecomidas.usuarios.infraestructure.out.jpa.repository;

import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(@NonNull Long id);
    void deleteById(@NonNull Long id);
}
