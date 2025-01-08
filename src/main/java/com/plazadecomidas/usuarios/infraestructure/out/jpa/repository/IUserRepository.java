package com.plazadecomidas.usuarios.infraestructure.out.jpa.repository;

import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(@NonNull String email);
    void deleteById(@NonNull Long id);
}
