package com.plazadecomidas.usuarios.infraestructure.out.jpa.repository;

import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    void deleteById(@NonNull Long id);
    UserEntity findByEmail(@NonNull String email);
    UserEntity findUserEntityById(@NonNull Long id);
}
