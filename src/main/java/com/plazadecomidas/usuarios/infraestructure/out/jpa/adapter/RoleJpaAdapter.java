package com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter;

import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.RoleEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role findRoleByName(String roleName) {
        return roleEntityMapper.toRole(roleRepository.findByName(roleName));
    }
}