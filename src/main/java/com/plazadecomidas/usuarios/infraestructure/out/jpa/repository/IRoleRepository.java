package com.plazadecomidas.usuarios.infraestructure.out.jpa.repository;

import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
